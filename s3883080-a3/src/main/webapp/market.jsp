<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.amazonaws.s3883080.*" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>Music Marketplace</title>
</head>
<body>
<h2>Music Marketplace</h2>
<h3>What kind of instrument are you looking for?</h3>
<form action="${pageContext.request.contextPath}/processInstrumentServlet" method="POST"><br>
<input type="hidden" id="guitar" name="instrument" value="guitar" required>
<input type="submit" value="Guitar"></form>

<form action="${pageContext.request.contextPath}/processInstrumentServlet" method="POST"><br>
<input type="hidden" id="bass" name="instrument" value="bass" required>
<input type="submit" value="Bass"></form>

<form action="${pageContext.request.contextPath}/processInstrumentServlet" method="POST"><br>
<input type="hidden" id="drums" name="instrument" value="drums" required>
<input type="submit" value="Drums"></form>

<form action="${pageContext.request.contextPath}/processInstrumentServlet" method="POST"><br>
<input type="hidden" id="saxophone" name="instrument" value="saxophone" required>
<input type="submit" value="Saxophone"></form>
<br><br>

<a href="main.jsp">Back to Main page</a>

</main>
<footer>
</footer>
</body>
</html>
