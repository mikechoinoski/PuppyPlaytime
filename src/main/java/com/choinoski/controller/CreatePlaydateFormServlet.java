package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
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

        //List<Integer> memberNumbers = new ArrayList();

        Map<String, String[]> myMap = request.getParameterMap();

        List<PackMember> allSearchedMembers = (List<PackMember>) session.getAttribute("searchMembers");

        List<PackMember> membersForPlaydate = new ArrayList<PackMember>();

        for (Map.Entry<String, String[]> entry : myMap.entrySet()) {

            String keyStr = entry.getKey().replace("memberCheckBox", "");
            //memberNumbers.add(Integer.parseInt(keyStr));
            for (PackMember currentMember: allSearchedMembers) {
                if (Integer.parseInt(keyStr) == currentMember.getPackMemberNumber()) {
                    membersForPlaydate.add(currentMember);
                }
            }
            //String[] value = (String[])entry.getValue();
        }

        session.setAttribute("membersForPlaydate", membersForPlaydate);

        String url = "/jsp/createNewPlaydate.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
