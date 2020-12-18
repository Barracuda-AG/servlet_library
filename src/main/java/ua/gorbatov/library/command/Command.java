package ua.gorbatov.library.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);

    boolean checkPermission(HttpServletRequest request);
}
