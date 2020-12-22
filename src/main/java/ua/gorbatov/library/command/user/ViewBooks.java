package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewBooks implements Command {

    private final BookService bookService;

    public ViewBooks(){
        bookService = ServiceFactory.getInstance().getBookService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
        return "/user/view_books.jsp";
    }
}
