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
    public List<Book> findAll(int offset, int noOfRecords, String sort, String sortDir) {
        return bookDao.findAll(offset, noOfRecords, sort, sortDir);
    }

    @Override
    public List<Book> findAll(int offset, int noOfRecords) {
        return bookDao.findAll(offset, noOfRecords);
    }

    @Override
    public List<Book> findByAuthorOrTitle(String text) {
        return bookDao.findByTitleOrAuthor(text);
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }

    @Override
    public void updateBookQuantity(int bookId, int quantity) {
        bookDao.updateBookQuantity(bookId, quantity);
    }

    @Override
    public void updateBook(int id, String title, String author, String publisher, int quantity) {
        bookDao.updateBook(id, title, author, publisher, quantity);
    }

    @Override
    public int getNoOfRecords() {
        return bookDao.getNoOfRecords();
    }
}
