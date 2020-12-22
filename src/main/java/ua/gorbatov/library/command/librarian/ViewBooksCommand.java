package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewBooksCommand implements Command {
    private final BookService bookService;

    public ViewBooksCommand() {
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
        return "/librarian/view_books.jsp";
    }
}
