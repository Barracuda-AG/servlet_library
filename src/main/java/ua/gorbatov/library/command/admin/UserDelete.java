package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserDelete implements Command {
    private final UserService userService;

    public UserDelete(){
        userService = ServiceFactory.getInstance().getUserService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");

        int id = Integer.parseInt(idString);
        userService.delete(id);

        return "/admin/view_users";
    }
}
