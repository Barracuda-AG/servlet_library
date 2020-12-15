package ua.gorbatov.library.dao.impl;

import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;
    private JDBCBookDao jdbcBookDao;

    public JDBCOrderDao(Connection connection){
        this.connection = connection;
        jdbcBookDao = new JDBCBookDao(this.connection);
    }
    @Override
    public void create(Order entity) {

        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (id,is_returned, issue_date, penalty, return_date) VALUES (?,?,?,?,?)")){

            ps.setInt(1, entity.getId());
            ps.setBoolean(2, entity.isReturned());
            ps.setDate(3, Date.valueOf(entity.getIssueDate()));
            ps.setInt(4, entity.getPenalty());
            ps.setDate(5, Date.valueOf(entity.getReturnDate()));
            ps.execute();

            insertBooks(entity.getBooks(), entity.getId());

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
                    order = extractFromResultSet(resultSet);
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
                Order order = extractFromResultSet(resultSet);
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
        PreparedStatement ps1 = connection.prepareStatement("DELETE FROM orders_books WHERE order_id = ?");
        PreparedStatement ps2 = connection.prepareStatement("UPDATE user SET order_id = null WHERE order_id = ?")){
            ps.setInt(1, id);
            ps1.setInt(1, id);
            ps2.setInt(1, id);

            ps2.execute();
            ps1.execute();
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
    private void insertBooks(List<Book> books, int orderId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO orders_books (order_id, books_id) VALUES (?,?)");

        for(Book book: books){
            int bookId = book.getId();
            ps.setInt(1, orderId);
            ps.setInt(2, bookId);
            ps.execute();
        }
    }
    private List<Book> getBooksFromOrder(int orderId) throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT books_id FROM orders_books WHERE order_id = ?");
        ps.setInt(1, orderId);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            int bookId = resultSet.getInt("books_id");
            Book book = jdbcBookDao.findById(bookId);
            books.add(book);
        }
        return books;
    }
    private Order extractFromResultSet(ResultSet resultSet) throws SQLException{
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setIssueDate(LocalDate.parse(resultSet.getString("issue_date")));
        order.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
        order.setPenalty(resultSet.getInt("penalty"));
        order.setReturned(Boolean.parseBoolean(resultSet.getString("is_returned")));
        order.setBooks(getBooksFromOrder(order.getId()));
        return order;
    }
}
