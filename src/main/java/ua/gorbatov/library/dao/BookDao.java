package ua.gorbatov.library.dao;

import ua.gorbatov.library.entity.Book;

public interface BookDao extends GenericDao<Book>{
    void updateBookQuantity(int bookId, int quantity);
}
