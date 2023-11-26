<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.amazonaws.s3883080.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>Jamsite - Marketplace</title>
    <% UserManager userManager = UserManager.getState();
        User user = userManager.getUser();
        String keyValue = (String) session.getAttribute("instrumentFocus");
    %>
</head>
<body>
<%
    DatabaseManager databaseManager = new DatabaseManager();
    Instrument instrument = databaseManager.getInstrument(keyValue);
    String ono = "";
    if (instrument.isOno()) {
        ono = " Neg.";
    }
    if (instrument.getSeller().equals(user.getEmail())) {
        out.print("You are the seller of this item, please try again.");
    } else {
        out.println("<h2>" + instrument.getInstrumentBrand() + " " + instrument.getInstrumentModel() + "</h2>" + instrument.getInstrumentType() + "<br>Location = " + instrument.getLocation() + ",<br><strong>Price = $" + instrument.getCost() + ono + "</strong><br>" + instrument.getDescription() + "<br> Selling by " + instrument.getSeller() + "<br><form action=\"http://s3883080a3-env.eba-iv36hhva.us-east-1.elasticbeanstalk.com/completePurchaseServlet\" method=\"POST\"><br><input type=\"hidden\" id=\"instrumentKey\" name=\"instrumentKey\" value=\"" + keyValue + "\"><input type=\"submit\" value=\"Buy\">");
    }
%>

</form>
<br><br>To return to main menu, <a href="main.jsp">Click Here</a>
</main>
<footer>
</footer>
</body>
</html>
