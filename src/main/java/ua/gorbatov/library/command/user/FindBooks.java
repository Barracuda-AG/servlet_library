package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBooks implements Command {

    private final BookService bookService;

    public FindBooks(){
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String text = request.getParameter("text");
        List<Book> bookList = bookService.findByAuthorOrTitle(text);
        request.setAttribute("bookList", bookList);

        return "/user/search_result.jsp";
    }
}
