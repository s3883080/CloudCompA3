<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.amazonaws.s3883080.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>Jamsite - Marketplace</title>
    <% UserManager userManager = UserManager.getState();
    User user = userManager.getUser();
    String instrumentTarget = (String) session.getAttribute("myInstrument");
    DatabaseManager databaseManager = new DatabaseManager();
    ArrayList<Instrument> instrumentsForSale = databaseManager.FetchInstruments(instrumentTarget); %>
</head>
<body>
<h2>Showing available <%out.print(instrumentTarget); %>'s </h2>
<%
    String serletLoc = "${pageContext.request.contextPath}/buyServlet";
    if (instrumentsForSale.isEmpty()) {
        out.print("There are no " + instrumentTarget + "'s for sale<br><br>");
    } else {
        for (int i = 0; i < instrumentsForSale.size(); i++) {
            out.print(instrumentsForSale.get(i).getInstrumentBrand() + " - " + instrumentsForSale.get(i).getInstrumentModel() + " " + instrumentsForSale.get(i).getInstrumentType() + "<form action=\"http://s3883080a3-env.eba-iv36hhva.us-east-1.elasticbeanstalk.com/buyServlet\" method='POST'><input type='hidden' name='keyValue' value=\"" + instrumentsForSale.get(i).getSeller() + "-" + instrumentsForSale.get(i).getInstrumentBrand() +"-" + instrumentsForSale.get(i).getInstrumentModel() + "\"><input type='submit' value='View Description'></form><br><br>");
        }
    }
%>
<br><br>To return to main menu, <a href="main.jsp">Click Here</a>
</main>
<footer>
</footer>
</body>
</html>
