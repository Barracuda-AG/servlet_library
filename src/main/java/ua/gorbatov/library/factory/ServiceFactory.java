package ua.gorbatov.library.factory;

import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;
import ua.gorbatov.library.service.impl.BookServiceImpl;
import ua.gorbatov.library.service.impl.OrderServiceImpl;
import ua.gorbatov.library.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private final UserService userService;
    private final OrderService orderService;
    private final BookService bookService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        orderService = new OrderServiceImpl();
        bookService = new BookServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
