package ua.gorbatov.library;

import ua.gorbatov.library.dao.BookDao;
import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();

        UserDao userDao = jdbcDaoFactory.createUserDao();
        OrderDao orderDao = jdbcDaoFactory.createOrderDao();
        BookDao bookDao = jdbcDaoFactory.createBookDao();


        List<Book> books = new ArrayList<>();
        books.add(bookDao.findById(2));
        books.add(bookDao.findById(10));

        User admin = userDao.findById(1);
        Order order = new Order();
        order.setId(4);
        order.setReturned(false);
        order.setIssueDate(LocalDate.now());
        order.setReturnDate(LocalDate.now().plusDays(5));
        order.setPenalty(0);
        order.setBooks(books);

        orderDao.create(order);
        userDao.setOrderToUser(admin, orderDao.findById(3));

       List<Order> orders = orderDao.findAll();
        System.out.println("========================All orders===========================");

        for(Order ord: orders){
           System.out.println(ord);
       }
        System.out.println("=========================Users================================");
        List<User> users = userDao.findAll();
        for(User user: users)
            System.out.println(user);
    }
}
