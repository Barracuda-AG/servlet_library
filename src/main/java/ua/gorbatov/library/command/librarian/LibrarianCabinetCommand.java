package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LibrarianCabinetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/librarian/cabinet.jsp";    }
}
