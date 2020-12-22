package ua.gorbatov.library.command;


import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;
import ua.gorbatov.library.util.StringValidator;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private final UserService userService;
    private final StringValidator stringValidator;

    public Registration() {
        userService = ServiceFactory.getInstance().getUserService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = "401.jsp";
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (checkParameters(firstName, lastName, email, password)) {
            userService.createUser(email, password, firstName, lastName);
            path = "login.jsp";
        }
        return path;
    }
    private boolean checkParameters(String firstName, String lastName, String email, String password) {
        boolean first = stringValidator.checkNameEn(firstName);
        boolean last = stringValidator.checkNameEn(lastName);
        boolean mail = stringValidator.checkEmail(email);
        boolean pass = stringValidator.checkPassword(password);
        return first && last && mail && pass;
    }
}
