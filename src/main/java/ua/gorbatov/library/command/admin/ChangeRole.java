package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeRole implements Command {

    private final UserService userService;

    public ChangeRole() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String role = request.getParameter("role");

        if (role.equals("ROLE_USER"))
            userService.changeRoleToLibrarian(Integer.parseInt(id));
        else
            userService.changeRoleToUser(Integer.parseInt(id));

        return "/admin/view_users";
    }
}
