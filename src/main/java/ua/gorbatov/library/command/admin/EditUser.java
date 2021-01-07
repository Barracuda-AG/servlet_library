package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUser implements Command {
    private final UserService userService;

    public EditUser() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        User userToEdit = userService.findById(Integer.parseInt(idString));

        request.setAttribute("user", userToEdit);

        return "/admin/edit_user.jsp";
    }
}
