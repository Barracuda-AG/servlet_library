package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BlockUser implements Command {

    private final UserService userService;

    public BlockUser() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        User user = userService.findById(Integer.parseInt(id));
        if (user.isAccountNonLocked()) {
            userService.lockUser(Integer.parseInt(id));
        } else {
            userService.unlockUser(Integer.parseInt(id));
        }

        return "/admin/view_users";
    }
}
