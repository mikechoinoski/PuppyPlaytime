package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 * @author mrchoinoski
 * @since  November 19, 2017
 */
@WebServlet(
        name = "insertNewPack",
        urlPatterns = { "/insertNewPack" }
)
public class CreatePackInsertServlet extends HttpServlet {

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

        GenericDao packDao = new GenericDao(Pack.class);

        Pack       newPack = new Pack(request.getParameter("packName"),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("address"),
                request.getParameter("phoneNumber"),
                request.getParameter("emailAddress"),
                request.getParameter("password"));




        String url = "/jsp/createNewPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
