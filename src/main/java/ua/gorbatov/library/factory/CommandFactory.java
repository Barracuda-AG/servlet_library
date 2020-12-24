package ua.gorbatov.library.factory;

import ua.gorbatov.library.command.*;
import ua.gorbatov.library.command.admin.*;
import ua.gorbatov.library.command.librarian.*;
import ua.gorbatov.library.command.user.CancelSubscription;
import ua.gorbatov.library.command.user.MakeOrder;
import ua.gorbatov.library.command.user.ShowOrder;
import ua.gorbatov.library.command.user.UserCabinetCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
    }

    static {
        commandMap.put("/login", new Login());
        commandMap.put("/logout", new Logout());
        commandMap.put("/404", new ErrorCommand());
        commandMap.put("/403", new AccessDeniedCommand());
        commandMap.put("/registration", new Registration());

        commandMap.put("/admin/cabinet", new AdminCabinetCommand());
        commandMap.put("/admin/addbook", new AddBook());
        commandMap.put("/admin/view_users", new ViewAllUsers());
        commandMap.put("/admin/addbook_post", new AddBookPost());
        commandMap.put("/admin/view_books", new ViewBooks());
        commandMap.put("/admin/delete_book", new BookDelete());
        commandMap.put("/admin/edit_book", new EditBook());
        commandMap.put(("/admin/update_book"), new UpdateBook());
        commandMap.put("/admin/delete_user", new UserDelete());
        commandMap.put("/admin/edit_user", new EditUser());
        commandMap.put("/admin/change_role", new ChangeRole());

        commandMap.put("/librarian/view_books", new ViewBooksCommand());
        commandMap.put("/librarian/cabinet", new LibrarianCabinetCommand());
        commandMap.put("/librarian/view_readers", new ViewReaders());
        commandMap.put("/librarian/cancel_order", new CancelOrder());
        commandMap.put("/librarian/view_subscriptions", new ViewSubscriptions());

        commandMap.put("/user/cabinet", new UserCabinetCommand());
        commandMap.put("/user/view_books", new ua.gorbatov.library.command.user.ViewBooks());
        commandMap.put("/user/make_order", new MakeOrder());
        commandMap.put("/user/show_order", new ShowOrder());
        commandMap.put("/user/cancel_order", new CancelSubscription());
    }

    public static CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public Command createCommand(HttpServletRequest request) {
        String url = request.getRequestURI().substring(request.getContextPath().length());
        Command command = commandMap.get(url);
        if (Objects.isNull(command)) {
            return new ErrorCommand();
        }
        return command;
    }
}
