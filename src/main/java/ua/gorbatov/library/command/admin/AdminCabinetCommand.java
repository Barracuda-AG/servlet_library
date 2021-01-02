package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;

import javax.servlet.http.HttpServletRequest;


public class AdminCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/admin/cabinet.jsp";
    }
}
