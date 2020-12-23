package ua.gorbatov.library.dao.impl;

import org.mindrot.jbcrypt.*;
import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private final JDBCOrderDao jdbcOrderDao;

    public JDBCUserDao(Connection connection){
        this.connection = connection;
        jdbcOrderDao = new JDBCOrderDao(this.connection);
    }
    @Override
    public void create(User entity) {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO user (email, first_name, last_name, password, role) VALUES (?,?,?,?,?)")){

                ps.setString(1, entity.getEmail());
                ps.setString(2, entity.getFirstName());
                ps.setString(3, entity.getLastName());
                ps.setString(4, BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt()));
                ps.setString(5, entity.getRole().toString());

                ps.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE role != 'ROLE_ADMIN'")){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?")){
            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try{
            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void setOrderToUser(User user, Order order){
        try(PreparedStatement ps = connection.prepareStatement("UPDATE user SET order_id = ? WHERE id = ?")){
            ps.setInt(1, order.getId());
            ps.setInt(2, user.getId());
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findOnlyLibrarians() {
        List<User> librarians = new ArrayList<>();
        try(Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE role = 'ROLE_LIBRARIAN'");

            while (resultSet.next()){
                User user = extractFromResultSet(resultSet);
                librarians.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return librarians;
    }

    @Override
    public List<User> findOnlyUsers() {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE role = 'ROLE_USER'");

            while (resultSet.next()){
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    @Override
    public User findAdmin(){
        User user = null;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE role = 'ROLE_ADMIN'");
            while (resultSet.next()){
                user = extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void changeRoleToLibrarian(int userId) {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")){
            ps.setString(1, Role.ROLE_LIBRARIAN.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeRoleToUser(int userId) {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")){
            ps.setString(1, Role.ROLE_USER.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")){
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
         if (user != null){
             if(!BCrypt.checkpw(password,user.getPassword())) user = null;
         }

        return user;
    }

    @Override
    public List<User> findUsersWithOrders() {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE order_id IS NOT NULL");

            while (resultSet.next()){
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException{

            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setRole(Role.valueOf(resultSet.getString("role")));
            user.setOrder(jdbcOrderDao.findById(resultSet.getInt("order_id")));

        return user;
    }
}
