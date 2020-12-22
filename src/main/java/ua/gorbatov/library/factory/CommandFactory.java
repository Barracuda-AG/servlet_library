package ua.gorbatov.library.factory;

import ua.gorbatov.library.command.*;
import ua.gorbatov.library.command.admin.AddBook;
import ua.gorbatov.library.command.admin.AddBookPost;
import ua.gorbatov.library.command.admin.AdminCabinetCommand;
import ua.gorbatov.library.command.admin.ViewAllUsers;
import ua.gorbatov.library.command.librarian.LibrarianCabinetCommand;
import ua.gorbatov.library.command.librarian.ViewBooksCommand;
import ua.gorbatov.library.command.user.MakeOrder;
import ua.gorbatov.library.command.user.ShowOrder;
import ua.gorbatov.library.command.user.UserCabinetCommand;
import ua.gorbatov.library.command.user.ViewBooks;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
//TODO create book delete command
//TODO create user delete command
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
        commandMap.put("/librarian/view_books", new ViewBooksCommand());
        commandMap.put("/librarian/cabinet", new LibrarianCabinetCommand());
        commandMap.put("/user/cabinet", new UserCabinetCommand());
        commandMap.put("/user/view_books", new ViewBooks());
        commandMap.put("/user/make_order", new MakeOrder());
        commandMap.put("/user/show_order", new ShowOrder());
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
