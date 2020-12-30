package ua.gorbatov.library.service;

import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.util.List;

public interface UserService {
    void createUser(String email, String password, String firstName, String lastName);
    void delete(int userId);
    User findById(int userId);
    List<User> findAllUsers();
    List<User> findOnlyLibrarians();
    List<User> findOnlyUsers();
    User findAdmin();
    void setOrderToUser(User user, Order order);
    void changeRoleToLibrarian(int userId);
    void changeRoleToUser(int userId);
    User getUserByEmailPassword(String email, String password);
    List<User> findUsersWithOrders();
    void lockUser(int id);
    void unlockUser(int id);
}
