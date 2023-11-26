<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.amazonaws.s3883080.*" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  <title>Login</title>
  <% DatabaseManager databaseManager = new DatabaseManager(); %>
</head>
<body>

  <h1>Musicians Space</h1>

  <h3>Login Page</h3>
  <form action="${pageContext.request.contextPath}/loginServlet" method="POST"><br>
    <fieldset>
        <%! String errorOnLogin = ""; %>
        <% if(session.getAttribute("loginError") != null) { %>
        <% errorOnLogin = (String) session.getAttribute("loginError");%>
        <% if (errorOnLogin.equals("exists")){ out.print("<div class='errorBox' style='color : red'>The Username or Password was incorrect</div>"); }}session.invalidate();%>
      <label for='email'>Email:</label>
      <input type='text' id="email" name='email' required><br><br>
      <label for='userPassword' >Password:</label>
      <input type='password' id='userPassword' name='userPassword' required ><br><br>
      <input type='submit' value='Login'><br>
  </form>
  <br>
  <a href="register.jsp">Please click here to register an account</a>
  </fieldset>
</body>
</html>
