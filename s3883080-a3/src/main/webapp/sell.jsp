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

<h2>Sell an Instrument</h2>

<fieldset>
    <form action="${pageContext.request.contextPath}/postInstrumentServlet" method="POST" ><br>
        <label for="instrumentType">Type of instrument: </label>
        <select id="instrumentType" name="instrumentType">
            <option value="guitar">guitar</option>
            <option value="bass">bass</option>
            <option value="drums">drums</option>
            <option value="saxophone">saxophone</option>
        </select><br><br>
        <label for='instrumentLocation'>Instrument Location: </label><br>
        <input type='text' id='instrumentLocation' name='instrumentLocation' value="<% out.print(user.getHometown()); %>" required><br><br>
        <label for='instrumentBrand'>Brand of Instrument: </label><br>
        <input type='text' id='instrumentBrand' name='instrumentBrand' required><br><br>
        <label for='instrumentModel'>Model of Instrument: </label><br>
        <input type='text' id='instrumentModel' name='instrumentModel' required><br><br>
        <label for='instrumentCost'>Selling Price of Item: </label><br>
        <input type='number' id='instrumentCost' name='instrumentCost' min='0' required><br><br>
        <label for='instrumentDescription' >Instrument Description: </label><br>
        <textarea name="instrumentDescription" id='instrumentDescription' name='instrumentDescription'  rows="10" cols="80" required ></textarea><br><br>
        <input type='submit' value='List your Ad'><br>

    </form>
</fieldset>

<br><br>To return to main menu, <a href="main.jsp">Click Here</a>
</main>
<footer>
</footer>
</body>
</html>
