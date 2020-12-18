package ua.gorbatov.library.service;

import ua.gorbatov.library.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void create(String title, String author, String publisher,
                LocalDate publishDate, Integer quantity);

    Book findById(int id);

    List<Book> findAll();

    void delete(int id);

    void updateBookQuantity(int bookId, int quantity);
}
