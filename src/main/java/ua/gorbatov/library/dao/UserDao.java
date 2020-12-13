package ua.gorbatov.library.dao;

import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
     boolean setOrderToUser(User user, Order order);
     List<User> findOnlyLibrarians();
     List<User> findOnlyUsers();
}
