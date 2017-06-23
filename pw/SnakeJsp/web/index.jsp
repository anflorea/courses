<%--
  Created by IntelliJ IDEA.
  User: flo
  Date: 04/06/2017
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<%!
  String someOutput(HttpSession sessionsa) {
    String message = (String) sessionsa.getAttribute("message");
    sessionsa.removeAttribute("message");
    if (message != null)
      return message + " <br>";
    return "";
  }
%>
<body>
<span> <%= someOutput(session) %></span>
<a href="login.jsp">Login</a> <br>
<a href="/game">Start game</a> <br>
<a href="/logout">Logout</a>
</body>
</html>
