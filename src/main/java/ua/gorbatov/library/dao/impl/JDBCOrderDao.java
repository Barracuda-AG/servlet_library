package ua.gorbatov.library.dao.impl;

import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO add list of Books to order
public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    public JDBCOrderDao(Connection connection){
        this.connection = connection;
    }
    @Override
    public void create(Order entity) {

        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (id, is_returned, issue_date, penalty, return_date) VALUES (?,?,?,?,?)")){
            Order order = new Order();
            ps.setInt(1, entity.getId());
            ps.setBoolean(2, entity.isReturned());
            ps.setDate(3, Date.valueOf(entity.getIssueDate()));
            ps.setInt(4, entity.getPenalty());
            ps.setDate(5, Date.valueOf(entity.getReturnDate()));

            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order findById(int id) {
        Order order = null;
            try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?")){
                ps.setInt(1,id);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()){
                    order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setIssueDate(LocalDate.parse(resultSet.getString("issue_date")));
                    order.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
                    order.setPenalty(resultSet.getInt("penalty"));
                    order.setReturned(Boolean.parseBoolean(resultSet.getString("is_returned")));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders")){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPenalty(resultSet.getInt("penalty"));
                order.setIssueDate(LocalDate.parse(resultSet.getString("issue_date")));
                order.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
                order.setReturned(Boolean.parseBoolean(resultSet.getString("is_returned")));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id = ?")){
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
