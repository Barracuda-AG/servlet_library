package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewAllUsers implements Command {
    private final UserService userService;

    public ViewAllUsers(){
        userService = ServiceFactory.getInstance().getUserService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.findAllUsers();
        request.setAttribute("users", users);
        return "/admin/view_users.jsp";
    }
}
