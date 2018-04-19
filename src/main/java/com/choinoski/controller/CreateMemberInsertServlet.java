package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

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

        String intactData = toUpperCase(request.getParameter("memberIntact"));
        String genderData = toUpperCase(request.getParameter("memberGender"));

        boolean memberIntact = false;
        char    maleOrFemale = ' ';

        boolean noErrorsFound =  true;

        if (intactData.equals("y") || intactData.equals("yes")) {
            memberIntact = true;
        }

        if (genderData.equals("male") || genderData.equals("m")) {
            maleOrFemale = 'M';
        } else if (genderData.equals("female") || genderData.equals("f")) {
            maleOrFemale = 'M';
        } else {
            noErrorsFound = false;
        }

        if (noErrorsFound) {
            //PackMember  newMember   = new PackMember(request.getParameter("packName"),
            //        request.getParameter("memberName"),
            //        request.getParameter("memberWeight"),
            //        request.getParameter("memberBreed"),
            //        maleOrFemale,
            //       request.getParameter("memberDateOfBirth"),
            //        memberIntact);

            //userPack.addMember(newMember);
        }

        String url = "/yourPack";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}