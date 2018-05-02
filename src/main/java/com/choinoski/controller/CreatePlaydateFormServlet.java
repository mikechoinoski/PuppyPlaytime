package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.persistence.LoggedInPack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

public class CreatePlaydateFormServlet extends HttpServlet {

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

        HttpSession  session      = request.getSession();
        LoggedInPack retrievePack = new LoggedInPack();

        session.setAttribute("userPack", retrievePack.loggedInPackInfo(request));

        Map myMap = request.getParameterMap();

        for (Object key : myMap.entrySet()) {
            String keyStr = (String)key;
            String[] value = (String[])myMap.get(keyStr);
            System.out.println("Key" + (String)key + "   :   " + Arrays.toString(value));
        }

        String url = "/jsp/createNewPlaydate.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
