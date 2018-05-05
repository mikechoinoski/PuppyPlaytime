package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Role;
import com.choinoski.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  This servlet creates a new pack from the information provided by a form. If the pack
 *  is successully create, the user is logged into the yourPack page.
 *
 * @author mrchoinoski
 */
@WebServlet(
        name = "insertNewPack",
        urlPatterns = { "/insertNewPack" }
)
public class CreatePackInsertServlet extends HttpServlet {

    /**
     *  Handles HTTP Get requests. Sets data for the HTTP request
     *  data. Redirects data to another servlet to display yourPack page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("yourPack");

    }

    /**
     *  Handles HTTP Post requests. Sets data for the HTTP request
     *  data. Redirects data to another servlet to display yourPack page.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao dao = new GenericDao(Pack.class);

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        String packName     = request.getParameter("packName");
        String packPassword = request.getParameter("password");

        Pack   newPack  = new Pack(packName,
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("address"),
                request.getParameter("phoneNumber"),
                request.getParameter("emailAddress"),
                packPassword);

        int id = dao.insert(newPack);

        if (id > 0) {
            Pack insertedPack = (Pack) dao.getById(id);
            insertedPack.addRole("user");
            session.setAttribute("packId", id);
            request.login(packName, packPassword);
        }

        response.sendRedirect("yourPack");

    }

}
