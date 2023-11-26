<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.amazonaws.s3883080.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>Jamsite - Sell an Instrument</title>
    <% UserManager userManager = UserManager.getState();
        User user = userManager.getUser(); %>
</head>
<body>

<h2>Add a photo to your listing</h2>

<p>Statistics show that adding a photo to your listing can greatly increase your chances of a sale. Please add a photo.</p>

<%
    String photoKey = (String) session.getAttribute("itemKey");
%>

<fieldset>
    <form action="/s3883080a3/addPhotoServlet" method="POST" enctype="multipart/form-data"><br>
        Attach Photo:<br>
        <input type="file" id="myFile" name="myFile"><br><br>
        <input type="hidden" id="photokey" value="<%out.print(photoKey);%>">
        <input type='submit' value='Attach photo to Advertisement'><br>
    </form>
</fieldset>

<br><br>To return to main menu, <a href="main.jsp">Click Here</a>
</main>
<footer>
</footer>
</body>
</html>
