<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<%@include file="/vendor/head.jsp"%>
<body>
<%@include file="/vendor/navBar.jsp"%>
</body>
<div class="container">

  <form action="/loginServlet" method="post">
    <br><br><br>
    <div>
      <label>Email address</label>
      <input type="email" name="email_login">
    </div>
    <br><br>
    <div>
      <label>Password</label>
      <input type="password" name="password_login">
    </div>
    <br><br>
    <button type="submit">Submit</button>
  </form>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>