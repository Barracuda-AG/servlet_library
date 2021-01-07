package ua.gorbatov.library.command.user;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewBooks implements Command {

    private final BookService bookService;

    public ViewBooks() {
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;
        String sort = Constants.ID;
        String sortDir = Constants.DESC;

        if (request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        if (request.getParameter(Constants.SORT) != null) {
            sort = request.getParameter(Constants.SORT);
        }
        if (request.getParameter(Constants.SORT_DIR) != null) {
            sortDir = request.getParameter(Constants.SORT_DIR);
        }

        List<Book> books = bookService.findAll((page - Constants.ONE) * recordsPerPage, recordsPerPage, sort, sortDir);
        int noOfRecords = bookService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("books", books);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("sort", sort);
        request.setAttribute("sortDir", sortDir);

        return "/user/view_books.jsp";
    }
}
