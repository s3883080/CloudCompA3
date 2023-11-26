package com.amazonaws.s3883080;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "AddPhotoServlet", value = "/addPhotoServlet")
public class AddPhotoServlet extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    @Override
    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String keyValue = req.getParameter("photokey");
        Part part = req.getPart("myFile");
        String filepath = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        InputStream inputStream = part.getInputStream();
        long length =(long) part.getSize();
        ProcessImage processImage = new ProcessImage();
        processImage.AddImageToBucket(filepath, keyValue, inputStream, length);
        req.getSession().setAttribute("instrumentAdded", keyValue);
        resp.sendRedirect("main.jsp");
    }
}
