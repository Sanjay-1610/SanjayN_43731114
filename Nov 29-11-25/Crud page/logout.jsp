<%
session.invalidate();     // destroy session
response.sendRedirect("index.jsp");   // go back to login
%>