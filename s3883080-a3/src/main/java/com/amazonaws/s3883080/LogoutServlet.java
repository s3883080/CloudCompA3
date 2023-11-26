package com.amazonaws.s3883080;

import java.io.IOException;
// import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "LogoutServlet", value = "/logoutServlet")

public class LogoutServlet extends HttpServlet {
  @Override
  public void init()
  {
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    UserManager userManager = UserManager.getState();
    userManager.logoutUser();
    resp.sendRedirect("index.jsp");
  }
}
