package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewSubscriptions implements Command {
    private final OrderService orderService;

    public ViewSubscriptions(){
        orderService = ServiceFactory.getInstance().getOrderService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orderList = orderService.findAll();
        request.setAttribute("orders", orderList);

        return "/librarian/view_subscriptions.jsp";
    }
}
