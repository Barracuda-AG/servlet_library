package ua.gorbatov.library.factory;

import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.command.ErrorCommand;
import ua.gorbatov.library.command.Login;
import ua.gorbatov.library.command.Logout;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory(){
    }
    static{
        commandMap.put("/login", new Login());
        commandMap.put("/logout", new Logout());
        commandMap.put("/404", new ErrorCommand());
    }
    public static CommandFactory getCommandFactory(){
        return commandFactory;
    }
    public Command createCommand(HttpServletRequest request){
        String url = request.getRequestURI().substring(request.getContextPath().length());
        Command command = commandMap.get(url);
        if(Objects.isNull(command)){
            return new ErrorCommand();
        }
        return command;
    }
}
