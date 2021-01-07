package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AddBook implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/admin/addbook.jsp";
    }
}
