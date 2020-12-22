package ua.gorbatov.library.command;

import ua.gorbatov.library.constant.Constants;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class Login implements Command{
    private final UserService userService;

    public Login(){
        userService = ServiceFactory.getInstance().getUserService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        String path = "";
//        if(!Objects.isNull(request.getSession().getAttribute(Constants.USER))) {
//            return "user/cabinet";
//        }
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        User user = userService.getUserByEmailPassword(email,password);

        if(Objects.isNull(user)) {
            path = "403";
        }
        else if(user.getRole().equals(Role.ROLE_USER)){
            request.getSession().setAttribute(Constants.USER, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "user/cabinet";
        }
        else if(user.getRole().equals(Role.ROLE_LIBRARIAN)){
            request.getSession().setAttribute(Constants.LIBRARIAN, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "librarian/cabinet";
        }else if(user.getRole().equals(Role.ROLE_ADMIN)){
            request.getSession().setAttribute(Constants.ADMIN, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "admin/cabinet";
        }
        return path;
    }

}
