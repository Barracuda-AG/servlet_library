package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class BookDelete implements Command {
    private final BookService bookService;

    public BookDelete(){
        bookService = ServiceFactory.getInstance().getBookService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");

        int id = Integer.parseInt(idString);
        bookService.delete(id);

        return "/admin/view_books";
    }
}
