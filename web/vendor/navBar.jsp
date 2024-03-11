<%@ page import="Classes.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<div class="container">
  <%
    User user1 = (User) request.getSession().getAttribute("currentSession");%>
  <nav class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">BITLAB NEWS</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/">News</a>
          </li>
          <%
            if(user1 == null){
          %>
          <li class="nav-item">
            <a class="nav-link" href="/loginServlet">Sign in</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/registerServlet">Register</a>
          </li>
          <%
          }
          else if (user1!=null){
          %>
                <li class="nav-item">
                  <a class="nav-link" href="/logOutServlet">Log out</a>
                </li>
          <%
            }
            if(user1!=null && user1.getRole_id().equals("1")){
              %>
                    <li class="nav-item">
                      <a class="nav-link" href="/addNewsServlet">Add news</a>
                    </li>
          <%
            }
          %>
        </ul>
      </div>
    </div>
  </nav>
</div>

</body>
</html>