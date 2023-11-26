package com.amazonaws.s3883080;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPhotoServlet", value = "/addPhotoServlet")
public class AddPhotoServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        boolean passed = false;
        String keyValue = req.getParameter("photokey");
        String imageUrl = req.getParameter("imageUrl");
        ProcessImage processImage = new ProcessImage();
        passed = processImage.AddImageToBucket(imageUrl, keyValue);

        req.getSession().setAttribute("instrumentAdded", keyValue);
        resp.sendRedirect("main.jsp");
    }
}
