package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class EditBook implements Command {
    private final BookService bookService;

    public EditBook(){
        bookService = ServiceFactory.getInstance().getBookService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");

        Book bookToEdit = bookService.findById(Integer.parseInt(idString));
        request.setAttribute("book",bookToEdit);

        return "/admin/edit_book.jsp";
    }
}
