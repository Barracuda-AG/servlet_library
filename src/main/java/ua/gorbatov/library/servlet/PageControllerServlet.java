package ua.gorbatov.library.servlet;


import ua.gorbatov.library.command.Command;
import ua.gorbatov.library.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageControllerServlet extends HttpServlet {
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.getCommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = COMMAND_FACTORY.createCommand(req);

        String path = command.execute(req);
        req.getRequestDispatcher(path).forward(req, resp);
    }

}
