package com.amazonaws.s3883080;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PostInstrumentServlet", value = "/postInstrumentServlet")
public class PostInstrumentServlet  extends HttpServlet {


    @Override
    public void init()
    {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        UserManager userManager = UserManager.getState();
        User user = userManager.getUser();
        DatabaseManager databaseManager = DatabaseManager.getState();
        String instrumentType = req.getParameter("instrumentType");
        String instrumentLocation = req.getParameter("instrumentLocation");
        String instrumentBrand = req.getParameter("instrumentBrand");
        String instrumentModel = req.getParameter("instrumentModel");
        String instrumentCost = (String) req.getParameter("instrumentCost");
        float price = Float.parseFloat(instrumentCost);
        String instrumentDescription = req.getParameter("instrumentDescription");
        Instrument instrument = new Instrument(user.getEmail(), instrumentLocation, instrumentType, instrumentBrand, instrumentModel, instrumentDescription, price, true, false);
        String itemKey = instrument.getSeller() + "-" + instrument.getInstrumentBrand() + "-" + instrument.getInstrumentModel();
        boolean success = databaseManager.AddInstrument(instrument, itemKey);
        if (success) {
            req.getSession().setAttribute("itemKey", itemKey);
            resp.sendRedirect("addphototolisting.jsp");
        }
    }
}