package ua.gorbatov.library.dao.impl;

import ua.gorbatov.library.dao.BookDao;
import ua.gorbatov.library.dao.DaoFactory;
import ua.gorbatov.library.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCBookDao implements BookDao {
    private Connection connection;

    public JDBCBookDao(Connection connection){
        this.connection = connection;
    }
    @Override
    public void create(Book entity) {

    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(int id) {

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
