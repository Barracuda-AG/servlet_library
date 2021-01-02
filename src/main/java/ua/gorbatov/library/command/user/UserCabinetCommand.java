package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserCabinetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/user/cabinet.jsp";
    }
}
