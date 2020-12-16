package ua.gorbatov.library.dao;

import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{
    List<Book> getBooksFromOrder(int orderId);
    int getLastId();
}
