package com.amazonaws.s3883080;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;

@WebServlet(name = "CompletePurchaseServlet", value = "/completePurchaseServlet")
public class CompletePurchaseServlet extends HttpServlet {


    @Override
    public void init()
    {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        String instrumentKey = req.getParameter("instrumentKey");
        UserManager userManager = UserManager.getState();
        DatabaseManager databaseManager = DatabaseManager.getState();
        User user = userManager.getUser();
        Instrument instrument = databaseManager.getInstrument(instrumentKey);
        String instrumentType = instrument.getInstrumentType();
        boolean updateToSold = databaseManager.UpdateInstrumentToSold(user, instrumentKey, instrumentType);
        if (updateToSold) {
            Receipt receipt = new Receipt(user.getEmail(), instrument.getSeller(), instrument.getInstrumentBrand(), instrument.getInstrumentModel(), instrument.getInstrumentType(), instrument.getCost());
            databaseManager.AddReceipt(receipt);
            req.getSession().setAttribute("receipt", instrumentKey);
            resp.sendRedirect("receipt.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}