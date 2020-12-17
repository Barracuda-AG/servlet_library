package ua.gorbatov.library.command.user;

import javax.servlet.http.HttpServletRequest;

public class MakeOrder extends AbstractUserCommand{
    @Override
    public String execute(HttpServletRequest request) {
        return "/user/cabinet.jsp";
    }
}
