package com.choinoski.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  This servlet logs the user out of the application
 *
 * @author mrchoinoski
 */
@WebServlet(
        name = "InvalidateUser",
        urlPatterns = { "/logOut" }
)
public class InvalidateUser extends HttpServlet {

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. It invalidates the user to log them out of the application. The user
     *  is redirected to the login screen.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        session.invalidate();

        response.sendRedirect("yourPack");


    }

}
