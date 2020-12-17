package ua.gorbatov.library.command.admin;

import javax.servlet.http.HttpServletRequest;


public class AdminCabinetCommand extends AbstractAdminCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "admin/cabinet.jsp";
    }
}
