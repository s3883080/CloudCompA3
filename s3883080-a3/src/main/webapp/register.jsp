<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.amazonaws.s3883080.*" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  <title>Register</title>
</head>
<body>
    <h2>Register Page</h2>
  <h3>Please fill in your details</h3>
  <fieldset>
    <form action="${pageContext.request.contextPath}/registrationServlet" method="POST" ><br>
      <label for="userEmail">Email:</label>
      <input type="text" id="userEmail" name="userEmail" required>
        <br><br>
        <label for="username">Name:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="hometown">Hometown:</label>
        <input type="text" id="hometown" name="hometown" required>
        <br><br>
      <label for="userPassword">Password:</label>
      <input type="password" id="userPassword" name="userPassword" required><br><br>
      <input type="submit" value="Register">
    </form>
    <%! String emailExists = ""; %>
    <% if(session.getAttribute("emailError") != null) { %>
    <% emailExists = (String) session.getAttribute("emailError");%>
    <% if (emailExists.equals("exists")){ out.print("<div class='errorBox' style='color : red'>That email is already in use, Please try a different value</div>");session.invalidate();}}%>
</fieldset>

  </main>
  <footer>
  </footer>
  </body>
  </html>
