package Servlets;

import Classes.DBManager;
import Classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/profileServlet")
public class ProfileServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email_profile");
        String fullname = req.getParameter("fullName_profile");
        String password = req.getParameter("password_profile");
        String repeat = req.getParameter("repeat_profile");
        String role = req.getParameter("role_profile");
        Long id = Long.valueOf(req.getParameter("id"));
        System.out.println(role);

        if (password.equals(repeat)){
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setFull_name(fullname);
            user.setPassword(password);
            user.setRole_id(role);


            if(DBManager.updateUser(user)) {

                resp.sendRedirect("/profileServlet");
            }
        }else{
            resp.sendRedirect("/profile?error");
        }
    }
}
