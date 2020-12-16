package ua.gorbatov.library.command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "404.jsp";
    }

    @Override
    public boolean checkPermission(HttpServletRequest request) {
        return true;
    }
}
