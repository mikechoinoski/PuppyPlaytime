package com.choinoski.controller;

import com.amazon.ImageVerifier;
import com.amazon.UploadImageToS3;
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
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *  This servlet is for creating a new user. The user is forwarded to the yourPack page after new
 *  member is created.
 *
 *  Sources used as example: https://www.journaldev.com/2122/servlet-3-file-upload-multipartconfig-part
 *
 * @author mrchoinoski
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class CreateMemberInsertServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Pack userPack;

    public static final String PERIOD = ".";

    private Properties properties;

    /**
     *  Handles HTTP POST request to create a new member. Information is verified and
     *  a new member is created. Data is set in the HTTP request. Data is forwarded to
     *  a JSP to display.
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
        properties = (Properties) servletContext.getAttribute("puppyPlaytimeProperties");

        boolean noErrorsFound = true;
        String  newFileName   = null;
        DataConverter dataConverter = null;
        String intactData   = null;
        String genderData   = null;
        LocalDate memberDob = null;
        Collection<Part> headerParts = null;

        String memberName = request.getParameter("memberName");

        userPack = (Pack) session.getAttribute("userPack");

        intactData = request.getParameter("memberIntact");
        if (!(intactData.equals("Yes") || intactData.equals("No"))) {
            noErrorsFound =  false;
        }

        genderData = request.getParameter("memberGender");
        if (!(genderData.equals("Male") || genderData.equals("Female"))) {
            noErrorsFound =  false;
        }

        memberDob = LocalDate.parse(request.getParameter("memberDateOfBirth"),
                DateTimeFormatter.ofPattern(properties.getProperty("form.date.format")));

        headerParts = request.getParts();

        dataConverter = new DataConverter(properties);

        try {
            newFileName = getFilesFromHeader(headerParts, memberName);
        } catch (Exception e) {
            logger.error("Error occurred while uploading a file: " + e);
        }

        if (noErrorsFound) {
            PackMember newMember = new PackMember(
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    dataConverter.getCharGender(genderData),
                    memberDob,
                    dataConverter.convertIntact(intactData),
                    newFileName);

            userPack.addMember(newMember);
        }


        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }


    /**
     *  This method processes an image and returns a filename if the image is correct.
     *
     * @param parts the parts of the form request
     * @param nameOfMember the name of the pack member.
     * @return the filename of the image to be inserted.
     *
     */
    private String getFilesFromHeader(Collection<Part> parts, String nameOfMember) {

        String     memberPictureFilename = "";
        String     generatedFilename     = "";
        String     fullFileName          = null;
        String     extension             = null;
        ByteBuffer imageBytes            = null;
        Boolean    isImageOfDog          = false;

        FileUtilities fileUtility = new FileUtilities();
        UploadImageToS3 uploader  = new UploadImageToS3(properties);
        ImageVerifier verifier    = new ImageVerifier(properties);

        try {
            for (Part part : parts) {
                memberPictureFilename = fileUtility.getFileName(part);
                if (!memberPictureFilename.equals("")) {
                    imageBytes = fileUtility.convertPartToBytes(part);
                    isImageOfDog = verifier.retrieveLabelsLocal(imageBytes,
                            properties.getProperty("aws.labels.label.to.check"));
                    if (isImageOfDog) {
                        generatedFilename = userPack.getPackName() + "_" + nameOfMember;
                        extension = FilenameUtils.getExtension(memberPictureFilename);
                        fullFileName = generatedFilename + PERIOD + extension;

                        try (InputStream inputStream = part.getInputStream()) {
                            uploader.transferFile(fullFileName, inputStream);
                        }

                    }
                }
            }
        } catch (IOException e) {
            logger.error("An IO exception occurred when reading the input stream of an image: " + e);
        } catch (Exception e) {
            logger.error("An exception occurred when converting an input part to bytes: " + e);
        }

        return fullFileName;
    }

}