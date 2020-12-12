package ua.gorbatov.library.dao.impl;

import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private JDBCOrderDao jdbcOrderDao;

    public JDBCUserDao(Connection connection){
        this.connection = connection;
        jdbcOrderDao = new JDBCOrderDao(connection);
    }
    @Override
    public void create(User entity) {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO user (id, email, first_name, last_name, password, role) VALUES (?,?,?,?,?,?)")){
                ps.setInt(1,entity.getId());
                ps.setString(2, entity.getEmail());
                ps.setString(3, entity.getFirstName());
                ps.setString(4, entity.getLastName());
                ps.setString(5, entity.getPassword());
                ps.setString(6, entity.getRole().toString());

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
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setOrder(jdbcOrderDao.findById(resultSet.getInt("order_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM user")){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setOrder(jdbcOrderDao.findById(resultSet.getInt("order_id")));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User entity) {

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
    public boolean setOrderToUser(User user, Order order){
        try(PreparedStatement ps = connection.prepareStatement("UPDATE user SET order_id = ? WHERE id = ?")){
            ps.setInt(1, order.getId());
            ps.setInt(2, user.getId());
            ps.execute();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
