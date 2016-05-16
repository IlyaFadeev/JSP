<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 02.11.2015
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
HttpSession httpSession = request.getSession();

%>
<h1>
    <%=httpSession.getAttribute("message")%>
</h1>
</body>
</html>
