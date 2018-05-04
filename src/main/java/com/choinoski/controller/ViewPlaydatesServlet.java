package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Playdate;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.LoggedInPack;
import com.choinoski.persistence.RequestParameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ViewPlaydatesServlet extends HttpServlet {

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

        HttpSession       session      = request.getSession();
        LoggedInPack      retrievePack = new LoggedInPack();
        RequestParameters myRequest    = new RequestParameters();

        GenericDao        dao          = new GenericDao(Playdate.class);

        session.setAttribute("userPack", retrievePack.loggedInPackInfo(request));

        List<Playdate> currentPlaydates = dao.getByPropertyGreaterDate("date", LocalDate.now());

        session.setAttribute("currentPlaydates", currentPlaydates);

        String url = "/jsp/viewPlaydates.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
