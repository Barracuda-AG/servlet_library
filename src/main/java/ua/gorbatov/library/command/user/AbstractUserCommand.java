package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public abstract class AbstractUserCommand implements Command {
    @Override
    public boolean checkPermission(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        return (Objects.nonNull(user) && user.getRole().equals(Role.ROLE_USER));
    }
}
