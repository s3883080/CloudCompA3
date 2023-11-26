<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.amazonaws.s3883080.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>Jamsite - Tax Receipt</title>
    <% UserManager userManager = UserManager.getState();
        User user = userManager.getUser();
        String instrumentKey = (String) session.getAttribute("receipt");
        DatabaseManager databaseManager = DatabaseManager.getState();
        Instrument instrument = databaseManager.getInstrument(instrumentKey);
    %>
</head>
<body>
<h2>Jamsite - Tax Receipt</h2><br>
<%
    out.println("<br> BUYER: " + user.getUsername() + "<br>\t" + user.getEmail() + "<br>\t" + user.getHometown());
    float preGst = (instrument.getCost()/11)*10;
    float gst = (instrument.getCost()/11);
    out.println("<br>ITEM:<br><h2>" + instrument.getInstrumentBrand() + " " + instrument.getInstrumentModel() + "</h2>" + instrument.getInstrumentType() + "<br>SOLD BY: " + instrument.getSeller() + " <br>Price (Excl GST) = $" + preGst + "<br> \t GST = $" + gst + " <br>GRAND TOTAL= $" + instrument.getCost());
%>
<br><br>To return to main menu, <a href="main.jsp">Click Here</a>
</main>
<footer>
</footer>
</body>
</html>
