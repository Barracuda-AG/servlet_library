package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowOrder implements Command {
    private final OrderService orderService;
    private final UserService userService;

    public ShowOrder() {
        orderService = ServiceFactory.getInstance().getOrderService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        user = userService.findById(user.getId());
        Order order = user.getOrder();
        List<Book> books = new ArrayList<>();

        if (order != null) {
            int orderId = order.getId();
            books = orderService.getBooksFromOrder(orderId);
        }
        request.setAttribute("order", order);
        request.setAttribute("books", books);

        return "/user/show_order.jsp";
    }
}
