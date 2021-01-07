package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewReaders implements Command {
    private final UserService userService;

    public ViewReaders() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.findUsersWithOrders();
        request.setAttribute("users", users);

        return "/librarian/view_readers.jsp";
    }
}
