package ua.gorbatov.library.service;

import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void create(List<Book> books);

    Order findById(int id);

    List<Order> findAll();

    void delete(int id);

    List<Book> getBooksFromOrder(int orderId);

    int getLastId();
}
