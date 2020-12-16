package ua.gorbatov.library;

import ua.gorbatov.library.dao.BookDao;
import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;
import ua.gorbatov.library.service.impl.BookServiceImpl;
import ua.gorbatov.library.service.impl.OrderServiceImpl;
import ua.gorbatov.library.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BookService bookService = new BookServiceImpl();
        OrderService orderService = new OrderServiceImpl();
//        List<Book> books =new ArrayList<>();
//        books.add(bookService.findById(1));
//        books.add(bookService.findById(5));
//
//        orderService.create(books);
//
//        userService.setOrderToUser(userService.findAdmin(), orderService.findById(orderService.getLastId()));

        System.out.println(orderService.findAll());
        System.out.println(userService.findAdmin());


    }
}
