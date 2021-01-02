package ua.gorbatov.library.dao.impl;

import ua.gorbatov.library.dao.BookDao;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookDao implements BookDao {
    private final Connection connection;
    private int noOfRecords;

    public JDBCBookDao(Connection connection){
        this.connection = connection;
    }
    @Override
    public void create(Book entity) {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO book (author, publish_date, publisher, quantity, title) VALUES (?,?,?,?,?)")){

            ps.setString(1, entity.getAuthor());
            ps.setDate(2, Date.valueOf(entity.getPublishDate()));
            ps.setString(3, entity.getPublisher());
            ps.setInt(4, entity.getQuantity());
            ps.setString(5, entity.getTitle());

            ps.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(LocalDate.parse(resultSet.getString("publish_date")));
                book.setPublisher(resultSet.getString("publisher"));
                book.setQuantity(resultSet.getInt("quantity"));

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM book")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(LocalDate.parse(resultSet.getString("publish_date")));
                book.setPublisher(resultSet.getString("publisher"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE id = ?")){
            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateBookQuantity(int bookId,int quantity) {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE book  SET quantity = ? WHERE id = ?")) {
           ps.setInt(1, quantity);
           ps.setInt(2, bookId);
           ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
            }
        }

    @Override
    public void updateBook(int id, String title, String author, String publisher, int quantity) {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE book SET quantity = ?,author = ?, title = ?, publisher = ? where id = ?")) {
            ps.setInt(1, quantity);
            ps.setString(2, author);
            ps.setString(3, title);
            ps.setString(4, publisher);
            ps.setInt(5, id);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> findByTitleOrAuthor(String text) {
        List<Book> books = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("select * from book where author like ? or title like ?")){
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(LocalDate.parse(resultSet.getString("publish_date")));
                book.setPublisher(resultSet.getString("publisher"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
            }
          return books;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll(int offset, int noOfRecords) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM book limit ?, ?")) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(LocalDate.parse(resultSet.getString("publish_date")));
                book.setPublisher(resultSet.getString("publisher"));
                book.setQuantity(resultSet.getInt("quantity"));
                books.add(book);
            }

            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if(resultSet.next()){
                this.noOfRecords = resultSet.getInt(1);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return books;
    }

    public int getNoOfRecords(){
        return noOfRecords;
    }

    @Override
    public void close() throws Exception {
        try{
            connection.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


}
