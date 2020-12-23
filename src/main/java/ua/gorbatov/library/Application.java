package ua.gorbatov.library;

import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;
import org.mindrot.jbcrypt.*;

public class Application {
    public static void main(String[] args) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        BookService bookService = ServiceFactory.getInstance().getBookService();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();



    }
}
