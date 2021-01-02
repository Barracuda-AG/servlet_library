package ua.gorbatov.library.command.librarian;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.entity.Book;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CancelOrder implements Command {

    private final OrderService orderService;
    private final BookService bookService;

    public CancelOrder(){
        orderService = ServiceFactory.getInstance().getOrderService();
        bookService = ServiceFactory.getInstance().getBookService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("orderId");
        List<Book> books = orderService.getBooksFromOrder(Integer.parseInt(id));
        orderService.delete(Integer.parseInt(id));

        for(Book book: books){
            bookService.updateBookQuantity(book.getId(), book.getQuantity()+1);
        }
        return "/librarian/view_readers";
    }
}
