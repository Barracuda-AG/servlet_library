package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//TODO return books when delete user
public class UserDelete implements Command {
    private final UserService userService;
    private final OrderService orderService;
    private final BookService bookService;

    public UserDelete() {
        userService = ServiceFactory.getInstance().getUserService();
        orderService = ServiceFactory.getInstance().getOrderService();
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        User user = userService.findById(id);
        List<Book> books = new ArrayList<>();

        if(user.getOrder() != null) {
            int orderId = user.getOrder().getId();
            books = user.getOrder().getBooks();
            orderService.delete(orderId);
            for(Book book: books){
                bookService.updateBookQuantity(book.getId(), book.getQuantity() + 1);
            }
        }
        userService.delete(id);

        return "/admin/view_users";
    }
}
