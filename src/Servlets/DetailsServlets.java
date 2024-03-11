package Servlets;

import Classes.Categories;
import Classes.Comment;
import Classes.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Classes.News;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/detailsNews")
public class DetailsServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        News news = DBManager.getNews(id);
        req.setAttribute("news" , news);

        List<Categories> categories = DBManager.getCategories();

        req.getSession().setAttribute("cat", categories);

        List<Comment> comments = DBManager.getCommentOfNews(id);
        req.setAttribute("comments", comments);

        req.getRequestDispatcher("detailsnews.jsp").forward(req, resp);
    }
}
