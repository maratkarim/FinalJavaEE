package Servlets;

import Classes.DBManager;
import Classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/loginServlet")
public class LoginServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email_login");
        String password = req.getParameter("password_login");

        User currentUser = DBManager.getUser(login);

        HttpSession session = req.getSession();
        session.setAttribute("currentSession", currentUser);

        String redirect = "";

        if (currentUser!=null){
            if (currentUser.getEmail().equals(login)){
                if (currentUser.getPassword().equals(password)){
                    redirect="/profileServlet";
                }else {
                    redirect = "/login?errorPasword";
                }
            }else{
                redirect="/login?errorEmail";
            }
        }else{
            redirect="/login?error";
        }
        resp.sendRedirect(redirect);
    }
}
