package ua.gorbatov.library;

import ua.gorbatov.library.dao.OrderDao;
import ua.gorbatov.library.dao.impl.JDBCConnection;
import ua.gorbatov.library.dao.impl.JDBCDaoFactory;
import ua.gorbatov.library.entity.Order;
import ua.gorbatov.library.entity.Role;
import ua.gorbatov.library.entity.User;
import ua.gorbatov.library.factory.ServiceFactory;
import ua.gorbatov.library.service.BookService;
import ua.gorbatov.library.service.OrderService;
import ua.gorbatov.library.service.UserService;
import org.mindrot.jbcrypt.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException{
        UserService userService = ServiceFactory.getInstance().getUserService();
        BookService bookService = ServiceFactory.getInstance().getBookService();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();


       OrderDao orderDao = jdbcDaoFactory.createOrderDao();
        Connection connection = JDBCConnection.getDataSource().getConnection();

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?")){

            ps.setInt(1, 2);
            Order order = null;
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                order = extractFromResultSet(resultSet);
            }
            System.out.println(order);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setIssueDate(LocalDate.parse(resultSet.getString("issue_date")));
        order.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
        order.setPenalty(resultSet.getInt("penalty"));
        Boolean isReturned = resultSet.getBoolean("is_returned");
        order.setReturned(isReturned);

        return order;
    }
}

