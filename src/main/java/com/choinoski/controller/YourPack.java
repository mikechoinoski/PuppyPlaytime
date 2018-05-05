package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.LoggedInPack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 *  This servlet shows the user their pack information and pack members. Administrators are redirected to another
 *  page that allows admin functions.
 *
 * @author mrchoinoski
 */
public class YourPack extends HttpServlet {

    private Properties properties;

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display pack information.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        GenericDao dao = new GenericDao(Pack.class);

        HttpSession    session        = request.getSession();
        LoggedInPack   retrievePack   = new LoggedInPack();

        String url = null;

        String packName = request.getUserPrincipal().getName();

        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");

        if (request.isUserInRole(properties.getProperty("role.user"))) {

            session.setAttribute("userPack", retrievePack.loggedInPackInfo(request));
            url = "/jsp/yourPack.jsp";

        } else if (request.isUserInRole(properties.getProperty("role.admin"))) {

            List<Pack> packs = dao.getAll();
            session.setAttribute("allPacks", packs);
            url = "/jsp/administrativeFunctions.jsp";

        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}