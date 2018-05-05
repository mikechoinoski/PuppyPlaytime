package com.choinoski.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


public class FileUtilities {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public static final String PERIOD = ".";

    public FileUtilities() {

    }

    public ByteBuffer convertPartToBytes(Part imagePart) throws Exception {

        ByteBuffer imageBytes;
        try (InputStream inputStream = imagePart.getInputStream()) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }

        return imageBytes;

    }

    public void verifyFolderExists(String folderPath) {

        File fileDirectory = new File(folderPath);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }

    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    public String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    /**
     * Utility method to upload a file to a folder
     */
    public void uploadfile(String folderLocation, String fileName, String fileExtension, Part part) {

        verifyFolderExists(folderLocation);

        String filePath = folderLocation + File.separator + fileName + PERIOD +  fileExtension;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            try {
                part.write(filePath);
            } catch (IOException e) {
                logger.error("Error occurred while uploading a file: " + e);
            }

        }

    }

}
