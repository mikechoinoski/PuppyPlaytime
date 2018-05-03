package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.LoggedInPack;
import com.choinoski.persistence.RequestParameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

public class CreatePlaydateFormServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests. Sets data for the HTTP request
     * data. Forwards data to a JSP to display.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session         = request.getSession();
        LoggedInPack retrievePack   = new LoggedInPack();
        RequestParameters myRequest = new RequestParameters();

        session.setAttribute("userPack", retrievePack.loggedInPackInfo(request));

        Map<String, String[]> checkBoxMappedValues = request.getParameterMap();

        List<PackMember> allSearchedMembers = (List<PackMember>) session.getAttribute("searchMembers");

        session.setAttribute("membersForPlaydate",
                myRequest.getMembersFromCheckbox(allSearchedMembers,checkBoxMappedValues));

        String url = "/jsp/createNewPlaydate.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }


}