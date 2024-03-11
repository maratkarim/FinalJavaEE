<%@ page import="java.util.List" %>
<%@ page import="Classes.Categories" %>
<%@ page import="Classes.News" %>
<%@ page import="Classes.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>AddNews</title>
</head>
<%@include file="/vendor/head.jsp"%>
<body>
<%@include file="/vendor/navBar.jsp"%>
</body>
<div class="container">
  <div>
  <%
    News news = (News) request.getAttribute("news");
    if (news!=null){
      if (user1!=null && user1.getRole_id().equals("1")){
    %>
    <form action="/updateServlet" method="post">
      <%
      }
      %>
          <br><br><br>
          <div>
            <input type="hidden" name="idDetails" value="<%=news.getId()%>">
            <label>Title</label>
            <input type="text" name="titleDetails" value="<%=news.getTitle()%>">
          </div>
          <div>
            <label>Content</label>
            <textarea name="contentDetails"> <%=news.getCategory()%></textarea>
          </div>
          <div>
            <label>Category</label>
            <select name="categoryDetails">
              <%
                List<Categories> categories = (List<Categories>) request.getSession().getAttribute("cat");
                if (categories!=null){
                  for (Categories cat: categories){
              %>
              <option value="<%=cat.getId()%>"> <%=cat.getName()%> </option>
              <%
                  }
                }
              %>
            </select>
          </div>
              <%
                if (user1!=null && user1.getRole_id().equals("1")){
              %>
                      <button type="submit">UPDATE NEWS</button>
                        </form>

                        <form action="/deleteServlet" method="post">
                          <input type="hidden" name="id" value="<%=news.getId()%>">
                          <button type="submit">DELETE NEWS</button>
                        </form>
              <%
                  }
              %>
          <div>

              <%
                List<Comment> comments = (List<Comment>) request.getAttribute("comments");
                if (comments != null){
                  for (Comment c: comments ){
              %>

                      <div>
                        <h5><%=c.getComment()%>></h5>
                        <p><%=c.getUser().getFull_name()%></p>
                      </div>

              <%
                  }
                }
              %>

          </div>
          <div>
              <%
                if(user1 != null){
              %>

                      <div>
                        <form action="/commentServlets" method="post">

                          <div>
                            <label>Comment</label>
                            <input type="text" name="comment" value="">
                          </div>
                          <div>
                            <input type="hidden" name="user_id" value="<%=user1.getId()%>">
                            <input type="hidden" name="news_id" value="<%=news.getId()%>">
                          </div>
                          <button type="submit" class="btn btn-success">Send</button>

                        </form>
                      </div>
              <%
                }
              %>
          </div>
    <%
      }
    %>
  </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>