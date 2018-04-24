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

    GenericDao dao;

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dao = new GenericDao(Pack.class);

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        String packName = request.getParameter("packName");

        Pack   newPack  = new Pack(packName,
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("address"),
                request.getParameter("phoneNumber"),
                request.getParameter("emailAddress"),
                request.getParameter("password"));

        int id = dao.insert(newPack);

        Pack insertedPack = (Pack) dao.getById(id);

        processRole(insertedPack);

        session.setAttribute("packId", id);

        //String url = "/YourPack";
        response.sendRedirect("yourPack");
        //RequestDispatcher dispatcher =
        //        getServletContext().getRequestDispatcher(url);
        //dispatcher.forward(request, response);

        //HttpServletResponse.sendRedirect("/YourPack");

    }

    public void processRole(Pack packForRole) {

        Role newPackRole  = null;

        dao = new GenericDao(Role.class);

        newPackRole = new Role("user");

        //id = dao.insert(newPackRole);
        //insertedRole = (Role) dao.getById(id);

        packForRole.addRole(newPackRole);

    }

}
