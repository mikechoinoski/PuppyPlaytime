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
import java.util.Set;

/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 * ./uploadedPhotos
 *
 * @author mrchoinoski
 * @since  November 19, 2017
 */
public class YourPack extends HttpServlet {

    private static final String ROOT_FOLDER = "/home/";
    private static final String LOGGED_IN_USER = System.getProperty("user.name");

    private static final String UPLOAD_FOLDER = ROOT_FOLDER + LOGGED_IN_USER +
            "/IdeaProjects/PuppyPlaytime/src/main/webapp/uploadedPhotos";
    private static final String UPLOAD_FOLDER2 = ROOT_FOLDER + LOGGED_IN_USER +
            "/IdeaProjects/PuppyPlaytime/target/PuppyPlaytime/uploadedPhotos/";

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

        GenericDao dao = new GenericDao(Pack.class);

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();
        LoggedInPack   retrievePack   = new LoggedInPack();
        String url      = null;
        //Principal verifiedUser = request.getUserPrincipal();
        //verifiedUser.isUserInRole();
        //isUserInRole(java.lang.String role);
        //getRemoteUser();

        String packName = request.getUserPrincipal().getName();

        if (request.isUserInRole("user")) {

            session.setAttribute("userPack", retrievePack.loggedInPackInfo(request));

            session.setAttribute("imageDirectory", UPLOAD_FOLDER);
            session.setAttribute("imageDirectory2", UPLOAD_FOLDER2);

            url = "/jsp/yourPack.jsp";
        } else if (request.isUserInRole("admin")) {
            List<Pack> packs = dao.getAll();
            session.setAttribute("allPacks", packs);
            url = "/jsp/administrativeFunctions.jsp";
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}