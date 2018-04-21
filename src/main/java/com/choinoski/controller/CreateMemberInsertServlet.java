package com.choinoski.controller;

import com.choinoski.entity.Pack;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

import com.choinoski.entity.PackMember;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 * @author mrchoinoski
 * @since  November 19, 2017
 */
@javax.servlet.annotation.MultipartConfig
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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        Pack userPack = (Pack) session.getAttribute("userPack");

        String intactData = request.getParameter("memberIntact");
        String genderData = request.getParameter("memberGender");

        boolean uploadPicture = false;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            uploadPicture = true;
        }

        boolean memberIntact = false;
        char    maleOrFemale = ' ';

        LocalDate memberDob = LocalDate.parse(request.getParameter("memberDateOfBirth"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        boolean noErrorsFound =  true;

        if (intactData.equals("yes")) {
            memberIntact = true;
        }

        if (genderData.equals("male")) {
            maleOrFemale = 'm';
        } else if (genderData.equals("female")) {
            maleOrFemale = 'f';
        } else {
            noErrorsFound = false;
        }

        if (noErrorsFound) {
            PackMember newMember = new PackMember(request.getParameter("packName"),
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    maleOrFemale,
                    memberDob,
                    memberIntact);

            userPack.addMember(newMember);
        }

        String url = "/yourPack";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}