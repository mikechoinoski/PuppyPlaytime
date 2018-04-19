package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 * @author mrchoinoski
 * @since  November 19, 2017
 */
@WebServlet(
        name = "createNewMember",
        urlPatterns = { "/createNewMember" }
)
public class CreateMemberInsertServlet extends HttpServlet {

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

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        Pack userPack = (Pack) session.getAttribute("userPack");

        String intactData = request.getParameter("memberIntact");
        String genderData = request.getParameter("memberGender");

        boolean memberIntact = false;
        char    maleOrFemale = ' ';

        if (intactData.equals("Yes") || intactData.equals("Y") || intactData.equals("yes")) {
            memberIntact = true;
        }

        if (genderData.equals("Male") || genderData.equals("Male") ) {
            memberIntact = true;
        }

        PackMember  newMember   = new PackMember(request.getParameter("packName"),
                request.getParameter("memberName"),
                request.getParameter("memberWeight"),
                request.getParameter("memberBreed"),
                ,
                request.getParameter("memberDateOfBirth"),
                memberIntact);

        userPack.addMember(newMember);

        session.setAttribute("packId", id);
        String url = "/jsp/createNewMember.jsp";

        RequestDispatcher  dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}