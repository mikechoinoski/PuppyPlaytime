package com.choinoski.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data for a Pack
 *
 * @author mrchoinoski
 */
@WebServlet(
        name = "createNewPack",
        urlPatterns = { "/createNewPack" }
)
public class CreatePackFormServlet extends HttpServlet {

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession    session        = request.getSession();

        session.removeAttribute("errorList");
        session.removeAttribute("packName");
        session.removeAttribute("firstName");
        session.removeAttribute("lastName");
        session.removeAttribute("address");
        session.removeAttribute("phoneNumber");
        session.removeAttribute("emailAddress");
        session.removeAttribute("password");

        String url = "/jsp/createNewPack.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
