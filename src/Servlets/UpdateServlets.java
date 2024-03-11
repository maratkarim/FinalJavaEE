package Servlets;

import Classes.Categories;
import Classes.DBManager;
import Classes.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(value = "/updateServlet")
public class UpdateServlets extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("idDetails");
        String title = req.getParameter("titleDetails");
        String content = req.getParameter("contentDetails");
        String categoryId = req.getParameter("categoryDetails");

        News news = DBManager.getNews(Long.valueOf(id));
        news.setTitle(title);
        news.setContent(content);

        Categories categories = DBManager.getCatById(Long.valueOf(categoryId));
        news.setCategory(categories);

        if(DBManager.updateNews(news)) {

            resp.sendRedirect("/mainServlet");
        }
    }
}
