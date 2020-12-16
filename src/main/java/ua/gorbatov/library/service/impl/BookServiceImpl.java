package ua.gorbatov.library.service.impl;

import ua.gorbatov.library.dao.BookDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.service.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl() {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        bookDao = jdbcDaoFactory.createBookDao();
    }

    @Override
    public void create(String title, String author, String publisher,
                       LocalDate publishDate, Integer quantity) {
        Book book = new Book(title, author, publisher, publishDate, quantity);
        bookDao.create(book);
    }

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }

    @Override
    public void updateBookQuantity(int bookId, int quantity) {
        bookDao.updateBookQuantity(bookId, quantity);
    }
}
