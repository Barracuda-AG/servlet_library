package ua.gorbatov.library.dao;

import ua.gorbatov.library.entity.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book>{
    void updateBookQuantity(int bookId, int quantity);

    void updateBook(int id, String title, String author, String publisher, int quantity);

    List<Book> findByTitleOrAuthor(String text);
}
