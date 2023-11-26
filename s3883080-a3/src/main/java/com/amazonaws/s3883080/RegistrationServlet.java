package com.amazonaws.s3883080;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registrationServlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void init() {}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        String name = req.getParameter("username");
        String hometown = req.getParameter("hometown");
        UserManager userManager = new UserManager();
        boolean doesEmailExist = userManager.DoesUserExist(email);
        if (doesEmailExist) {
            req.getSession().setAttribute("emailError", "exists");
            resp.sendRedirect("register.jsp");
        } else {
            userManager.AddUser(email, name, password, hometown);
            resp.sendRedirect("index.jsp");
        }
    }
}
