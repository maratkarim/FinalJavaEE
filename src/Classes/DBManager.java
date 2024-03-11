package Classes;

import Classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Connection connection;
    static{
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/news",
                    "postgres",
                    "3220");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerUser(User user){
        try{
            PreparedStatement stmt = connection.prepareStatement(
              "INSERT INTO users (email, password, full_name, role_id)" +
                      "VALUES (?, ?, ?, '2')");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFull_name());
            int rows=stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e) {
            e.printStackTrace(); // Печать исключения для отладки
            throw new RuntimeException("Failed to register user", e); // Перебросить исключение
        }
    }
    public static User getUser(String email){
        User user=null;

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ?"
            );
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getLong("id"));
                user.setRole_id(resultSet.getString("role_id"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return user;
    }
    public static boolean updateUser(User user){

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET email=?, password=?, full_name=?," +
                            "role_id=? WHERE id=?;");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFull_name());
            stmt.setString(4, user.getRole_id());
            stmt.setLong(5, user.getId());
            int rows=stmt.executeUpdate();
            stmt.close();
            return rows>0;
        }catch (SQLException e) {
            e.printStackTrace(); // Печать исключения для отладки
            throw new RuntimeException("Failed to register user", e); // Перебросить исключение
        }
    }
    public static List<Categories> getCategories(){
        List<Categories> categories = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM news_cat"
            );
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Categories category = new Categories();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
    public static Categories getCatById(Long id){
        Categories category = new Categories();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM news_cat WHERE id=?"
            );
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()){

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
    public static boolean addNews(News news){
        int rows=0;
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO news (post_date, category_id, title, content) VALUES (Now(), ?, ?, ?)"

            );
            stmt.setLong(1, news.getCategory().getId());
            stmt.setString(2, news.getContent());
            stmt.setString(3, news.getContent());

            rows=stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows>1;
    }

    public static List<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT news.id, news.post_date, news.title, news_cat.id, news_cat.name, news.content FROM " +
                            "news INNER JOIN news_cat on news.category_id = news_cat.id"
            );
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getLong("id"));
                news1.setPost_date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                Categories category = new Categories();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));

                news1.setCategory(category);

                news.add(news1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }
    public static News getNews(Long id){
        News news = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT news.id, news.post_date, news.title, news_cat.id, news_cat.name, news.content FROM " +
                            "news INNER JOIN news_cat on news.category_id = news_cat.id WHERE news.id=?"
            );
            stmt.setLong(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                news = new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPost_date(resultSet.getTimestamp("post_date"));

                Categories cat = new Categories();
                cat.setId(resultSet.getLong("id"));
                cat.setName(resultSet.getString("name"));
                news.setCategory(cat);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }
    public static void deleteItem(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE from news WHERE id = ? ");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
    public static boolean updateNews(News news) {
        int rows=0;
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE news SET title=?, post_date= Now(), category_id=?, content=? WHERE id=? ");

            stmt.setString(1, news.getTitle());
            stmt.setLong(2, news.getCategory().getId());
            stmt.setString(3, news.getContent());
            stmt.setLong(4, news.getId());

            rows=stmt.executeUpdate();
            stmt.close();

        } catch (Exception var3) {
            var3.printStackTrace();
        }
        return rows>0;
    }

    public static boolean addComment(Comment comment){
        int rows=0;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO comments (comment, post_date, user_id, news_id) VALUES (?, Now(), ?, ?)"

            );
            stmt.setString(1, comment.getComment());
            stmt.setLong(2, comment.getUser().getId());
            stmt.setLong(3, comment.getNews().getId());

            rows=stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows>0;
    }
    public static List<Comment> getCommentOfNews(Long id){
        List<Comment> comments = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT c.id, c.comment, c.post_date, u.id, u.full_name " +
                            "FROM comments AS c INNER JOIN users u on u.id = c.user_id WHERE c.news_id=?"
            );
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPost_date(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFull_name(resultSet.getString("full_name"));

                comment.setUser(user);
                comments.add(comment);
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return comments;
    }
}
