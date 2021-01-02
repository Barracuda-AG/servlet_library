package ua.gorbatov.library.service;

import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void create(String title, String author, String publisher,
                LocalDate publishDate, Integer quantity);

    Book findById(int id);

    List<Book> findAll();

    List<Book> findAll(int offset, int noOfRecords);

    List<Book> findByAuthorOrTitle(String text);

    void delete(int id);

    void updateBookQuantity(int bookId, int quantity);

    void updateBook(int id, String title, String author, String publisher, int quantity);

    int getNoOfRecords();
}
