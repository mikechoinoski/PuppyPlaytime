package com.choinoski.amazon;

import com.amazon.UploadImageToS3;
import org.junit.jupiter.api.Test;


        import com.choinoski.entity.Pack;
        import com.choinoski.test.util.Database;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.nio.file.Path;
        import java.nio.file.Paths;

public class UploadFileTest {

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @Test
    void setUp() {

        UploadImageToS3 uploader = new UploadImageToS3();

        //uploader.transferFile("newfile.jpg", null);


    }
}
