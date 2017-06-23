<%--
  Created by IntelliJ IDEA.
  User: flo
  Date: 27/05/2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String user = null;
    if (session != null)
        user = (String) session.getAttribute("userName");
    if (user == null) {
        if (session != null)
            session.setAttribute("error", "Please login first");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "login.jsp");
    }
%>
<html>
<head>
    <title>File Uploading Form</title>
</head>
<body>
<a href="index.jsp">Home</a>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="UploadServlet" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50" />
    <br />
    <input type="submit" value="Upload File" />
</form>
</body>
</html>