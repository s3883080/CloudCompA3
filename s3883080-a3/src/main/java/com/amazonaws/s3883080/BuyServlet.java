package com.amazonaws.s3883080;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyServlet", value = "/buyServlet")
public class BuyServlet extends HttpServlet {

    @Override
    public void init() {}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String keyValue = req.getParameter("keyValue");
        DatabaseManager databaseManager = DatabaseManager.getState();
        req.getSession().setAttribute("instrumentFocus", keyValue);
        resp.sendRedirect("instrumentviewer.jsp");
    }
}
