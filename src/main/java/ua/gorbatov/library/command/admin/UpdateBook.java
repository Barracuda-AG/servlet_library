package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.util.StringValidator;

import javax.servlet.http.HttpServletRequest;

public class UpdateBook implements Command {
    private final BookService bookService;
    private final StringValidator stringValidator;

    public UpdateBook(){
        bookService = ServiceFactory.getInstance().getBookService();
        stringValidator = StringValidator.getInstance();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String quantityString = request.getParameter("quantity");
        String publishDateString = request.getParameter("publishDate");

        if(stringValidator.checkBookParameters(title, author, publisher, publishDateString, quantityString)){
            int id = Integer.parseInt(idString);
            int quantity = Integer.parseInt(quantityString);
            bookService.updateBook(id,title, author, publisher,quantity);
        }
        return "/admin/view_books";
    }
}
