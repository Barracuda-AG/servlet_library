package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
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

        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;

        if(request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        List<Book> books = bookService.findAll((page - Constants.ONE)*recordsPerPage, recordsPerPage);
        int noOfRecords = bookService.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("books", books);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/librarian/view_books.jsp";
    }
}
