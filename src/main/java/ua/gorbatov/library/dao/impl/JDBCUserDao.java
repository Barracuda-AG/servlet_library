package ua.gorbatov.library.dao.impl;

import org.mindrot.jbcrypt.*;
import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private final JDBCOrderDao jdbcOrderDao;
    private int noOfRecords;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
        jdbcOrderDao = new JDBCOrderDao(this.connection);
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO user (email, first_name, last_name, password, role, account_non_locked) VALUES (?,?,?,?,?,?)")) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
            ps.setString(5, entity.getRole().toString());
            ps.setBoolean(6, entity.isAccountNonLocked());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE role != 'ROLE_ADMIN'")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll(int offset, int noOfRecords) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select SQL_CALC_FOUND_ROWS * from user left join orders on user.order_id = orders.id WHERE role != 'ROLE_ADMIN' limit ?,?")) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setAccountNonLocked(resultSet.getBoolean("account_non_locked"));
                user.setOrder(new Order(resultSet.getInt("order_id")));

                users.add(user);
            }
            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOrderToUser(User user, Order order) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET order_id = ? WHERE id = ?")) {
            ps.setInt(1, order.getId());
            ps.setInt(2, user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findAdmin() {
        User user = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE role = 'ROLE_ADMIN'");
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void changeRoleToLibrarian(int userId) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")) {
            ps.setString(1, Role.ROLE_LIBRARIAN.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeRoleToUser(int userId) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")) {
            ps.setString(1, Role.ROLE_USER.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            if (!BCrypt.checkpw(password, user.getPassword())) user = null;
        }

        return user;
    }

    @Override
    public List<User> findUsersWithOrders() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE order_id IS NOT NULL");

            while (resultSet.next()) {
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void lockUser(int id) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET account_non_locked = false WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unlockUser(int id) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET account_non_locked = true WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setAccountNonLocked(resultSet.getBoolean("account_non_locked"));
        user.setOrder(jdbcOrderDao.findById(resultSet.getInt("order_id")));

        return user;
    }
}
