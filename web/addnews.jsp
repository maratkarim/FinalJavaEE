<%@ page import="java.util.List" %>
<%@ page import="Classes.Categories" %>
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
  <form action="/addNewsServlet" method="post">
    <br><br><br>
    <div>
      <label>Title</label>
      <input type="text" name="titleAddNews">
    </div>
    <div>
      <label>Content</label>
      <textarea name="contentAddNews"></textarea>
    </div>
    <div>
      <label>Category</label>
      <select name="categoryAddNews">
        <%
          List<Categories> categories = (List<Categories>) request.getAttribute("cat");
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
    <button type="submit">ADD NEWS</button>
  </form>



</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>