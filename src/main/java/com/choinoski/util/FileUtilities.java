package com.choinoski.util;

import org.apache.commons.io.IOUtils;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;


public class FileUtilities {
    //private static final long serialVersionUID = 1L;

    //private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    //private static final int MAX_REQUEST_SIZE = 1024 * 1024;

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

}
