<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%
String user = (String) session.getAttribute("userid");
if (user == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP CRUD Example</title>
</head>
<body>
<h1>JSP CRUD Example</h1>
<a href="adduserform.jsp">Add User</a>
<a href="viewusers.jsp">View Users</a>
<a href="logout.jsp">Logout</a>
</body>
</html>