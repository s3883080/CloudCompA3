package com.amazonaws.s3883080;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    public void init()
    {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        String password = req.getParameter("userPassword");
        UserManager userManager = new UserManager();
        boolean loginSuccess = userManager.LoginUser(email, password);
        if (loginSuccess) {
            User user = userManager.returnUser(email, password);
            userManager.setUser(user);
            userManager.setState(userManager);
            resp.sendRedirect("main.jsp");
        } else {
            req.getSession().setAttribute("loginError", "exists");
            resp.sendRedirect("index.jsp");
        }
    }
}