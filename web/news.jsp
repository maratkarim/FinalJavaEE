<%@ page import="java.util.List" %>
<%@ page import="Classes.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="/vendor/head.jsp"%>
<body>
<%@include file="/vendor/navBar.jsp"%>
</body>
<div class="container"></div>

<div>
    <%
        List<News> news = (List<News>) request.getAttribute("news");
        if (news!=null){
            for (News n : news){
                %>
                    <div class="container" style="border: darkgray 1px solid">
                        <h5><%=n.getTitle()%></h5>
<%--                        <p><%=n.getContent()%></p>--%>
                        <p><%=n.getPost_date()%></p>
                        <p><%=n.getCategory().getName()%></p>
                        <a href="/detailsNews?id=<%=n.getId()%>" class="btn btn-primary">Read more</a>
                    </div>
                <%
            }
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>