package com.choinoski.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


/**
 * A class of utilities for files.
 *
 * @author mchoinoski
 */
public class FileUtilities {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The constant PERIOD.
     */
    public static final String PERIOD = ".";

    /**
     * Instantiates a new File utilities.
     */
    public FileUtilities() {

    }

    /**
     * Convert part to a byte buffer.
     *
     * @param imagePart the image part
     * @return the byte buffer
     * @throws Exception the exception
     */
    public ByteBuffer convertPartToBytes(Part imagePart)  {

        ByteBuffer imageBytes = null;

        try (InputStream inputStream = imagePart.getInputStream()) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            logger.error("An IO Exception occurred when reading an input stream and " +
                    "converting it to bytes: " + e);
        }

        return imageBytes;

    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     *
     * @param part the part
     * @return the file name
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

}
