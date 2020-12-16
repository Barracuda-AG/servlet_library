package ua.gorbatov.library;

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

//        List<Book> books =new ArrayList<>();
//        books.add(bookService.findById(1));
//        books.add(bookService.findById(5));
//
//        orderService.create(books);
//
//        userService.setOrderToUser(userService.findAdmin(), orderService.findById(orderService.getLastId()));


//        for(User user: userService.findAllUsers())
//            System.out.println(user);
        String encryptedPass = BCrypt.hashpw("pass",BCrypt.gensalt());
        boolean check = BCrypt.checkpw("pass",encryptedPass);
     //   userService.createUser("crypt@mail.com", encryptedPass, "user2","user2");
        User user = userService.getUserByEmailPassword("crypt@mail.com", "pass");
        System.out.println(user);
    }
}
