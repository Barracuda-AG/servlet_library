package ua.gorbatov.library.command.admin;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookPost implements Command {

    private final BookService bookService;
    private final StringValidator stringValidator;

    public AddBookPost(){
        bookService = ServiceFactory.getInstance().getBookService();
        stringValidator = StringValidator.getInstance();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String path = "/401.jsp";
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publishDateString = request.getParameter("publishDate");
        String quantityString = request.getParameter("quantity");
        if (checkParameters(title, author, publisher, publishDateString, quantityString)) {
            LocalDate publishDate = LocalDate.parse(publishDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Integer quantity = Integer.valueOf(quantityString);
            bookService.create(title, author, publisher, publishDate, quantity);
            path = "/admin/cabinet";
        }
        return path;
    }
    private boolean checkParameters(String title, String author, String publisher, String publishDateString, String quantity) {
        boolean name = stringValidator.checkBookTitle(title);
        boolean auth = stringValidator.checkBookTitle(author);
        boolean publish = stringValidator.checkBookTitle(publisher);
        boolean date = stringValidator.checkDate(publishDateString);
        boolean number = stringValidator.checkInteger(quantity);
        return name && auth && publish && date && number;
    }
}
