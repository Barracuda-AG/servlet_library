package ua.gorbatov.library.service.impl;

import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        userDao = jdbcDaoFactory.createUserDao();
    }

    @Override
    public void createUser(String email, String password, String firstName, String lastName) {
        User user = new User(email, password, firstName, lastName, Role.ROLE_USER, true);
        userDao.create(user);
    }

    @Override
    public void delete(int userId) {
        userDao.delete(userId);
    }

    @Override
    public User findById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findAdmin() {
        return userDao.findAdmin();
    }

    @Override
    public void setOrderToUser(User user, Order order) {
        userDao.setOrderToUser(user, order);
    }

    @Override
    public void changeRoleToLibrarian(int userId) {
        userDao.changeRoleToLibrarian(userId);
    }

    @Override
    public void changeRoleToUser(int userId) {
        userDao.changeRoleToUser(userId);
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        return userDao.getUserByEmailPassword(email, password);
    }

    @Override
    public List<User> findUsersWithOrders() {
        return userDao.findUsersWithOrders();
    }

    @Override
    public void lockUser(int id) {
        userDao.lockUser(id);
    }

    @Override
    public void unlockUser(int id) {
        userDao.unlockUser(id);
    }

    @Override
    public List<User> findAll(int offset, int noOfRecords) {
        return userDao.findAll(offset, noOfRecords);
    }

    @Override
    public int getNoOfRecords() {
        return userDao.getNoOfRecords();
    }
}
