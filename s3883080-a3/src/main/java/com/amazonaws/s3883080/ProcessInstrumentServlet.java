package com.amazonaws.s3883080;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProcessInstrumentServlet", value = "/processInstrumentServlet")
public class ProcessInstrumentServlet extends HttpServlet {

    @Override
    public void init() {}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String instrumentSelection = req.getParameter("instrument");
        req.getSession().setAttribute("myInstrument", instrumentSelection);
        resp.sendRedirect("instrumentmarket.jsp");
    }
}
