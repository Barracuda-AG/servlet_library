package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
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
        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;

        if(request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        List<Order> orders = orderService.findAll((page - Constants.ONE)*recordsPerPage, recordsPerPage);
        int noOfRecords = orderService.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("orders", orders);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/librarian/view_subscriptions.jsp";
    }
}
