package ua.gorbatov.library.dao;

import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    void setOrderToUser(User user, Order order);

    List<User> findOnlyLibrarians();

    List<User> findOnlyUsers();

    User findAdmin();

    void changeRoleToLibrarian(int userId);

    void changeRoleToUser(int userId);

    User getUserByEmailPassword(String email, String password);
}
