package ua.gorbatov.library;

import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.dao.UserDao;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.User;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        UserDao userDao = jdbcDaoFactory.createUserDao();
        OrderDao orderDao = jdbcDaoFactory.createOrderDao();

        Order order = orderDao.findById(1);
        User admin = userDao.findById(1);

        userDao.setOrderToUser(admin,order);

        System.out.println(userDao.findById(1));

    }
}
