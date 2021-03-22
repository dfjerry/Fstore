package controller;

import da.DAO;
import entity.Product;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        try {
            switch (action){
                case "login":
                    login(request, response);
                    break;
                case "check":
                    checkUser(request, response);
                    break;
                case "list":
                    listProduct(request, response);
                    break;
                case "logout":
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
    private void checkUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        List<User> listUser = DAO.selectAllUsers();
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(username, password);
        if (DAO.checkUser(user)){
            HttpSession session = request.getSession();
            session.setAttribute("bean", user);
            response.sendRedirect("list");
        }
        response.sendRedirect("login");
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        response.sendRedirect("login.jsp");
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ClassNotFoundException{
        response.sendRedirect("login.jsp");
    }
}
