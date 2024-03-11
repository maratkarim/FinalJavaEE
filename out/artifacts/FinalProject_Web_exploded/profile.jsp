<%@ page import="Classes.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
</head>
<%@include file="/vendor/head.jsp"%>
<body>
<%@include file="/vendor/navBar.jsp"%>
</body>
<div class="container">

  <form action="/profileServlet" method="post">
    <br><br><br>
    <%
      User user = (User) request.getSession().getAttribute("currentSession");
      if(user!=null){
    %>
          <br><br>
          <div>
            <label>Email address</label>
            <input type="email" name="email_profile" value="<%=user.getEmail()%>" readonly>
          </div>
          <br><br>
          <div>
            <label>Full name</label>
            <input type="text" name="fullName_profile" value="<%=user.getFull_name()%>">
          </div>
          <br><br>
          <div>
            <label>Password</label>
            <input type="password" name="password_profile" value="<%=user.getPassword()%>">
          </div>
          <br><br>
          <div>
            <label>Repeat password</label>
            <input type="password" name="repeat_profile" value="<%=user.getPassword()%>">
          </div>
          <div>
            <input type="text" name="role_profile" value="<%=user.getRole_id()%>">
          </div>
          <div>
              <input type="hidden" name="id" value="<%=user.getId()%>">
          </div>
          <br><br>
          <button type="submit">Update</button>
    <%
      }
    %>

  </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>