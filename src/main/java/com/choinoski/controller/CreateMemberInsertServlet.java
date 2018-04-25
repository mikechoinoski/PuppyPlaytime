package com.choinoski.controller;

import com.amazon.ImageVerifier;
import com.amazon.VerifyImage;
import com.choinoski.entity.Pack;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.choinoski.entity.PackMember;
import com.choinoski.util.FileUtilities;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;


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

    String sourceUploadFolder;
    String targetUploadFolder;
    Pack   userPack;

    String fileExtension;

    public static final String PERIOD = ".";

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
            getFilesFromHeader(headerParts, generatedFilename);
        } catch (Exception e) {

        }

        if (noErrorsFound) {
            PackMember newMember = new PackMember(
                    request.getParameter("memberName"),
                    request.getParameter("memberWeight"),
                    request.getParameter("memberBreed"),
                    convertGender(genderData),
                    memberDob,
                    convertIntact(intactData),
                    generatedFilename + PERIOD + fileExtension);

            userPack.addMember(newMember);
        }


        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private void getFilesFromHeader(Collection<Part> parts, String generatedFilename) throws Exception{

        verifyFolderExists(sourceUploadFolder);
        verifyFolderExists(sourceUploadFolder);

        String sourceFilename        = "";
        String targetFilename        = "";
        String memberPictureFilename = "";

        ImageVerifier verifier       = new ImageVerifier();
        FileUtilities convertToBytes = new FileUtilities();
        ByteBuffer    imageBytes     = null;
        Boolean       imageIsCorrect = false;

        for (Part part : parts) {
            memberPictureFilename = getFileName(part);
            if (!memberPictureFilename.equals("")) {
                imageBytes = convertToBytes.convertPartToBytes(part);
                imageIsCorrect = verifier.retrieveLabelsLocal(imageBytes,70,"Dog");
                if (imageIsCorrect) {
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
                }
            }
        }

    }

    private void verifyFolderExists(String folderPath) {

        File fileDirectory = new File(folderPath);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
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
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    //https://stackoverflow.com/questions/14618953/image-conversion-in-java

    //private File convertImageToJPG(File unconvertedFile) throws Exception {
    //File inputFile = new File("/path/to/image.png");
    //File outputFile = new File("Test.jpg");
    //try (InputStream is = new FileInputStream(inputFile)) {
    //BufferedImage image = ImageIO.read(is);
    //try (OutputStream os = new FileOutputStream(outputFile)) {
    //ImageIO.write(image, "jpg", os);
    //} catch (Exception exp) {
    //exp.printStackTrace();
    //}
    //} catch (Exception exp) {
    //exp.printStackTrace();
    //}

    //return outputFile;

    //}







}