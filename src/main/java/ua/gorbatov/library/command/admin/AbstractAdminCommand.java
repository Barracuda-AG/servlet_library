package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public abstract class AbstractAdminCommand implements Command {

    @Override
    public boolean checkPermission(HttpServletRequest request) {
        User admin = (User)request.getSession().getAttribute(Constants.ADMIN);
        return (Objects.nonNull(admin)&& admin.getRole().equals(Role.ROLE_ADMIN));
    }
}
