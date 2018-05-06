package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataConverterTest {

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @Test
    void setUp() {

        Path pathAbsolute = Paths.get("/home/student/IdeaProjects/PuppyPlaytime/target/classes/puppyPlaytime.properties");
        Path pathBase = Paths.get("/home/student/IdeaProjects/PuppyPlaytime/target/classes/com/choinoski/persistence/DataConverter.class");
        //Path pathRelative = pathBase.relativize(pathAbsolute);


    }

}
