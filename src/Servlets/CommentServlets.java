package Servlets;

import Classes.Comment;
import Classes.DBManager;
import Classes.News;
import Classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/commentServlets")
public class CommentServlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        Long user_id = Long.valueOf(req.getParameter("user_id"));
        Long news_id = Long.valueOf(req.getParameter("news_id"));
        System.out.println(comment);

        Comment comment1 = new Comment();
        comment1.setComment(comment);

        News news = new News();
        news.setId(news_id);
        comment1.setNews(news);

        User user = new User();
        user.setId(user_id);
        comment1.setUser(user);

        if (DBManager.addComment(comment1)){
            resp.sendRedirect("/mainServlet");
        }
    }
}
