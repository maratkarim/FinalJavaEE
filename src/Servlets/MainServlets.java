package Servlets;

import Classes.DBManager;
import Classes.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/mainServlet")
public class MainServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> news = DBManager.getNews();
        req.setAttribute("news", news);
        req.getRequestDispatcher("/news.jsp").forward(req, resp);
    }
}
