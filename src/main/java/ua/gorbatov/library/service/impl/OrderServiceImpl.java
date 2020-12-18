package ua.gorbatov.library.service.impl;

import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.service.OrderService;

import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(){
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        orderDao = jdbcDaoFactory.createOrderDao();
    }


    @Override
    public void create(List<Book> books) {
        Order order = new Order(orderDao.getLastId() + 1,LocalDate.now(), LocalDate.now().plusDays(10),
                false, 0, books);
        orderDao.create(order);
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void delete(int id) {
        orderDao.delete(id);
    }

    @Override
    public List<Book> getBooksFromOrder(int orderId) {
        return orderDao.getBooksFromOrder(orderId);
    }

    @Override
    public int getLastId() {
        return orderDao.getLastId();
    }
}
