package com.choinoski.controller;

import com.amazon.ImageVerifier;
import com.choinoski.entity.Pack;
import java.io.*;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.DataConverter;
import com.choinoski.util.FileUtilities;
import org.apache.commons.io.FilenameUtils;


/**
 *  This servlet sets HTTP request data and forwards it to a JSP
 *  to display data.
 *
 *  Sources used: https://www.journaldev.com/2122/servlet-3-file-upload-multipartconfig-part
 *
 * @author mrchoinoski
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class CreateMemberInsertServlet extends HttpServlet {

    String sourceUploadFolder;
    String targetUploadFolder;
    Pack   userPack;

    String fileExtension;

    public static final String PERIOD = ".";

    private Properties properties;

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
        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");


        boolean noErrorsFound = true;
        String  newFileName   = null;

        DataConverter dataConverter = new DataConverter();


        String generatedFilename;

        userPack = (Pack) session.getAttribute("userPack");

        sourceUploadFolder  = (String) session.getAttribute("imageDirectory");
        targetUploadFolder = (String) session.getAttribute("imageDirectory2");

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

        Collection<Part> headerParts = request.getParts();

        generatedFilename = userPack.getPackName() + "_" + request.getParameter("memberName");

        try {
            int returnCode = getFilesFromHeader(headerParts, generatedFilename);
            if (returnCode == 1) {
                newFileName = generatedFilename + PERIOD + fileExtension;
            }

        } catch (Exception e) {

        }

        if (noErrorsFound) {
            PackMember newMember = new PackMember(
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    dataConverter.getCharGender(genderData),
                    memberDob,
                    convertIntact(intactData),
                    newFileName);

            userPack.addMember(newMember);
        }


        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }


    /**
     *  Handles HTTP GET requests. Sets data for the HTTP request
     *  data. Forwards data to a JSP to display.
     *
     *@return the status of the upload (1 is correct image found, 0 is incorrect image found, -1 is no image found)
     *
     */

    private int getFilesFromHeader(Collection<Part> parts, String generatedFilename) throws Exception{

        FileUtilities fileUtility = new FileUtilities();

        int uploadStatus = -1;

        fileUtility.verifyFolderExists(sourceUploadFolder);
        fileUtility.verifyFolderExists(sourceUploadFolder);

        String sourceFilename        = "";
        String targetFilename        = "";
        String memberPictureFilename = "";

        ImageVerifier verifier       = new ImageVerifier(properties);
        FileUtilities convertToBytes = new FileUtilities();
        ByteBuffer    imageBytes     = null;
        Boolean       isImageOfDog   = false;

        for (Part part : parts) {
            memberPictureFilename = fileUtility.getFileName(part);
            if (!memberPictureFilename.equals("")) {
                imageBytes = convertToBytes.convertPartToBytes(part);
                isImageOfDog = verifier.retrieveLabelsLocal(imageBytes,
                        properties.getProperty("aws.labels.label.to.check"));
                if (isImageOfDog) {
                    fileExtension = FilenameUtils.getExtension(memberPictureFilename);
                    sourceFilename = sourceUploadFolder + File.separator + generatedFilename + PERIOD +  fileExtension;
                    File sourceFile = new File(sourceFilename);
                    if (!sourceFile.exists()) {
                        part.write(sourceFilename);
                    }
                    targetFilename = targetUploadFolder + File.separator + generatedFilename + PERIOD +  fileExtension;
                    File targetFile = new File(targetFilename);
                    if (!targetFile.exists()) {
                        part.write(targetFilename);
                    }
                    uploadStatus = 1;
                } else {
                    uploadStatus = 0;
                }
            }
        }

        return uploadStatus;

    }



    private boolean convertIntact(String intactData) {

        boolean memberIntact = false;
        if (intactData.equals("yes")) {
            memberIntact = true;
        }

        return memberIntact;

    }



}