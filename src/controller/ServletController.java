package controller;

import da.DAO;
import entity.Product;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/")
public class ServletController extends javax.servlet.http.HttpServlet {
    private DAO DAO;
    public void init() {DAO = new DAO();}
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action){
                case "/login":
                    login(request, response);
                    break;
                case "/check":
                    System.out.println("vao check");
                    check(request, response);
                    break;
                case "/list":
                    listProduct(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    login(request, response);
                    break;
            }
        }catch (SQLException | ClassNotFoundException ex){
            throw new ServletException(ex);
        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        List<Product> listProduct = DAO.selectAllProducts();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
        dispatcher.forward(request, response);
    }
    private void check(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        HttpSession session = request.getSession();
        if (DAO.checkUser(user) == true){
            System.out.println("ok");
            session.setAttribute("user", user);
            List<Product> listProduct = DAO.selectAllProducts();
            request.setAttribute("listProduct", listProduct);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        }else{
            System.out.println("sai roi");
            session.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
            response.sendRedirect("login");
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
