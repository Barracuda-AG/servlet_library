package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public abstract class AbstractLibrarianCommand implements Command {

    @Override
    public boolean checkPermission(HttpServletRequest request) {
        User admin = (User)request.getSession().getAttribute(Constants.LIBRARIAN);
        return (Objects.nonNull(admin)&& admin.getRole().equals(Role.ROLE_LIBRARIAN));
    }
}
