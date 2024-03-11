package Servlets;

import Classes.User;
import Classes.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/registerServlet")
public class RegisterServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email_register");
        String fullname = req.getParameter("fullName_register");
        String password = req.getParameter("password_register");
        String repeat = req.getParameter("repeat_register");

        System.out.println(email);
        System.out.println(fullname);
        System.out.println(password);

        if (password.equals(repeat)){
            User user = new User();
            user.setEmail(email);
            user.setFull_name(fullname);
            user.setPassword(password);

            System.out.println(user.getEmail());
            System.out.println(user.getFull_name());
            System.out.println(user.getPassword());

            DBManager.registerUser(user);

            resp.sendRedirect("/loginServlet");
        }else{
            resp.sendRedirect("/register?passwordNotEqual");
        }
    }
}
