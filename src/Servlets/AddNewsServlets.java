package Servlets;

import Classes.Categories;
import Classes.DBManager;
import Classes.News;
import Classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/addNewsServlet")
public class AddNewsServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        User user = (User) req.getSession().getAttribute("currentSession");
//        if(user!=null && user.getRole_id().equals("1")){
//            req.getRequestDispatcher("/addnews.jsp").forward(req, resp);
//        }else{
//            resp.sendRedirect("/error");
//        }
        List<Categories> categories = DBManager.getCategories();
        req.setAttribute("cat", categories);
        req.getRequestDispatcher("addnews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("titleAddNews");
        String content = req.getParameter("contentAddNews");
        Long cat_id = Long.valueOf(req.getParameter("categoryAddNews"));

        News news = new News();
        news.setTitle(title);
        news.setContent(content);

        Categories category = new Categories();
        category.setId(cat_id);
        news.setCategory(category);

        DBManager.addNews(news);
        resp.sendRedirect("/mainServlet");
    }
}
