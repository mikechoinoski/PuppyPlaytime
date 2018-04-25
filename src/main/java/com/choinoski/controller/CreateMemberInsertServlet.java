package com.choinoski.controller;

import com.choinoski.entity.Pack;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.choinoski.entity.PackMember;



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

    String sourceUploadFolder
    String uploadFolder2

    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display.
     *
     *@param request the HttpServletRequest object
     *@param response the HttpServletResponse object
     *@exception ServletException if there is a Servlet failure
     *@exception IOException if there is an IO failure
     */

    //private static final String UPLOAD_DIR = "/uploaded_pictures";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        HttpSession    session        = request.getSession();

        boolean noErrorsFound =  true;

        Pack userPack = (Pack) session.getAttribute("userPack");


        String intactData = request.getParameter("memberIntact");
        if (!(intactData.equals("yes") || intactData.equals("no"))) {
            noErrorsFound =  false;
        }

        String genderData = request.getParameter("memberGender");
        if (!(genderData.equals("male") || genderData.equals("female"))) {
            noErrorsFound =  false;
        }

        LocalDate memberDob = LocalDate.parse(request.getParameter("memberDateOfBirth"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));





        File fileSaveDir = new File(uploadFolder);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        Collection<Part> headerParts = request.getParts();









        if (noErrorsFound) {
            PackMember newMember = new PackMember(
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    convertGender(genderData),
                    memberDob,
                    convertIntact(intactData),
                    memberPictureFilename);

            userPack.addMember(newMember);
        }


        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private void getFilesFromHeader(Collection<Part> parts) {

        String sourceFilename = null;
        String targetFilename = null;
        String memberPictureFilename = null;

        for (Part part : parts) {
            memberPictureFilename = getFileName(part);
            if (!memberPictureFilename.equals("")) {
                sourceFilename = uploadFolder + File.separator + memberPictureFilename;
                File sourceFile = new File(sourceFilename);
                if (!sourceFile.exists()) {
                    part.write(sourceFilename);
                }
                targetFilename = uploadFolder2 + File.separator + memberPictureFilename;
                File targetFile = new File(sourceFilename);
                if (!targetFile.exists())
                    part.write(targetFilename);
            }
        }

    }

    private boolean convertIntact(String intactData) {

        boolean memberIntact = false;
        if (intactData.equals("yes")) {
            memberIntact = true;
        }

        return memberIntact;

    }

    private char convertGender(String gender) {
        char    maleOrFemale = ' ';

        if (gender.equals("male")) {
            maleOrFemale = 'm';
        } else if (gender.equals("female")) {
            maleOrFemale = 'f';
        }

        return maleOrFemale;

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