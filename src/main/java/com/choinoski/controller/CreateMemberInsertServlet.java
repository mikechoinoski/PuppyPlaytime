package com.choinoski.controller;

import com.choinoski.entity.Pack;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

import com.choinoski.entity.PackMember;
import com.choinoski.util.FileUtilities;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 *  Sources used: https://www.journaldev.com/2122/servlet-3-file-upload-multipartconfig-part
 *
 * @author mrchoinoski
 * @since  November 19, 2017
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
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
    private static final String UPLOAD_FOLDER = "/home/student/IdeaProjects/PuppyPlaytime/uploaded_pictures";

    //private static final String UPLOAD_DIR = "/uploaded_pictures";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        Pack userPack = (Pack) session.getAttribute("userPack");

        String intactData = request.getParameter("memberIntact");
        String genderData = request.getParameter("memberGender");

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

        String memberPictureFilename = null;

        File fileSaveDir = new File(UPLOAD_FOLDER);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        //String fileName = null;
        for (Part part : request.getParts()) {
            memberPictureFilename = getFileName(part);
            if (!memberPictureFilename.equals("")) {
                part.write(UPLOAD_FOLDER + File.separator + memberPictureFilename);
            }
        }

        if (noErrorsFound) {
            PackMember newMember = new PackMember(
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    maleOrFemale,
                    memberDob,
                    memberIntact,
                    memberPictureFilename);

            userPack.addMember(newMember);
        }


        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

}