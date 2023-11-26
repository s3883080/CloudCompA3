<%@ page import="com.amazonaws.s3883080.User" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.amazonaws.s3883080.Instrument" %>
<%@ page import="com.amazonaws.s3883080.DatabaseManager" %>
<%@ page import="com.amazonaws.s3883080.UserManager" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07/11/2023
  Time: 06:11 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jam-site</title>
    <%
        DatabaseManager databaseManager = DatabaseManager.getState();
        UserManager userManager = UserManager.getState();
        User user = userManager.getUser();
        String email = user.getEmail();
        ArrayList<Instrument> myInstruments = databaseManager.FetchMyInstruments(email);
    %>
</head>
<body>
<header class="forumHeader">
    <h1>Jam-site</h1>
</header>
<div>
    Want to buy Music equipment? <a href="market.jsp">Click Here!</a><br><br>
    Have an instrument you want to sell? <a href="sell.jsp">Click Here!</a><br><br>
</div>
<div>
    <%
        if (myInstruments.isEmpty()) {
            out.println("You currently have no Instruments for sale<br><br>");
        } else {
            out.print("<h2>Current Instruments for sale</h2><br>");
            for (int i = 0; i < myInstruments.size(); i++) {
                out.print(myInstruments.get(i).getInstrumentBrand() + " - " + myInstruments.get(i).getInstrumentModel() + " " + myInstruments.get(i).getInstrumentType() + " $"  + myInstruments.get(i).getCost() + "<br><br>");
            }
        }

    %>
</div>

<div>
<%
    String address = "https://5ypb4pab6e.execute-api.us-east-1.amazonaws.com/beta/receipts/?buyer=" + user.getEmail();
        out.print("Welcome " + user.getUsername() + "<br>" + email + "<br><br><form action='https://5ypb4pab6e.execute-api.us-east-1.amazonaws.com/beta/receipts/' method='GET'><input type='hidden' id='buyer' name='buyer' value='" + user.getEmail() + "'><input type='submit' value='Find my Receipts'></form>");
        // <a href=\"" + address + "\">Find my Receipts</a>");
        %>
    <form action="${pageContext.request.contextPath}/logoutServlet" method="POST">
        <input type='submit' value='Logout'>
    </form>
</div>

<div>
    <%
        if(session.getAttribute("instrumentAdded") != null) {
            String instrumentKey = (String) session.getAttribute("instrumentAdded");
            Instrument instrument = databaseManager.getInstrument(instrumentKey);
            out.print("<div class='instrumentAdded' style='color : green'>" + instrument.getInstrumentBrand() + " - " + instrument.getInstrumentModel() + " was successfully listed, Good luck</div>");
            session.invalidate();
        }
    %>
</div>

</body>
</html>
