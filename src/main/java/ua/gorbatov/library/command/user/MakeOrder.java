package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MakeOrder implements Command {
    private final BookService bookService;
    private final OrderService orderService;
    private final UserService userService;

    public MakeOrder() {
        bookService = ServiceFactory.getInstance().getBookService();
        orderService = ServiceFactory.getInstance().getOrderService();
        userService = ServiceFactory.getInstance().getUserService();

    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        user = userService.findById(user.getId());
        int id = user.getId();
        List<Book> booksToOrder = getBooksFromRequest(request);
        String path = "/user/cabinet.jsp";

        if(booksToOrder.size() > 0 && user.getOrder() == null) {
            orderService.create(booksToOrder);
            Order order = orderService.findById(orderService.getLastId());
            userService.setOrderToUser(user, order);
            request.getSession().setAttribute(Constants.USER,userService.findById(id));
            path = "/user/show_order";
        }

        return path;
    }

    private List<Book> getBooksFromRequest(HttpServletRequest request) {
        String[] bookIds = request.getParameterValues("bookId");
        List<Book> booksToOrder = new ArrayList<>();
        if (bookIds.length != 0) {
            for (String id : bookIds) {
                int intId = Integer.parseInt(id);
                booksToOrder.add(bookService.findById(intId));
            }
        }
        return booksToOrder;
    }
}
