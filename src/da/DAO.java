package da;

import entity.Product;
import entity.User;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/fstore?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "";
    private  static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String CHECK_USERS = "SELECT username FROM users WHERE username like ? AND password like ? ";
    protected static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }
    public List<Product> selectAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String productname = rs.getString("productname");
            int price = rs.getInt("price");
            String desc = rs.getString("desc");
            int amount = rs.getInt("amount");
            products.add(new Product(id, productname, price, desc, amount));
        }
        return products;
    }
    public List<User> selectAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            users.add(new User(id, username, password));
        }
        return users;
    }
    public boolean checkUser(User user) throws SQLException, ClassNotFoundException{
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(CHECK_USERS);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            return rowUpdate = true;
        }
        rowUpdate = false;
        return rowUpdate;
    }
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        if (getConnection() !=null ){
//            System.out.println("ok");
//        }
//    }
}
