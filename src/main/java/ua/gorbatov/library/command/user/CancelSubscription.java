package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CancelSubscription implements Command {
    private final OrderService orderService;
    private final BookService bookService;


    public CancelSubscription() {
        orderService = ServiceFactory.getInstance().getOrderService();
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER);

        String id = request.getParameter("id");
        List<Book> books = orderService.getBooksFromOrder(Integer.parseInt(id));
        orderService.delete(Integer.parseInt(id));

        user.setOrder(null);
        request.getSession().setAttribute(Constants.USER, user);

        for (Book book : books) {
            bookService.updateBookQuantity(book.getId(), book.getQuantity() + 1);
        }
        return "/user/show_order";
    }

}
