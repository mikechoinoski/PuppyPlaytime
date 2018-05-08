package com.choinoski.persistence;

import com.amazon.ImageVerifier;
import com.amazonaws.services.rekognition.model.Label;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;
import static org.h2.util.SortedProperties.loadProperties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageVerifierTest {

    private Properties properties;
    ImageVerifier verifier;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        try {
            properties = loadProperties("/home/student/IdeaProjects/PuppyPlaytime/src/test/resources/puppyPlaytime.properties");
            verifier = new ImageVerifier(properties);
        } catch (IOException e) {
            Assert.fail();
        }

    }

    /**
     * Verify when a label isn't in a list
     */
    @Test
    void testSearchForLabelWhenNotFound() {

        List<Label> allLabels = new ArrayList();

        Label labelOne = new Label();
        Label labelTwo = new Label();
        Label labelThree = new Label();

        labelOne.setName("Chicken");
        labelTwo.setName("Monkey");
        labelThree.setName("Goose");

        allLabels.add(labelOne);
        allLabels.add(labelTwo);
        allLabels.add(labelThree);

        assertTrue(!verifier.searchForLabel( allLabels, "Dog"));
    }

    /**
     * Verify when a label is in a list
     */
    @Test
    void ttestSearchForLabelWhenFound() {

        List<Label> allLabels = new ArrayList();

        Label labelOne = new Label();
        Label labelTwo = new Label();
        Label labelThree = new Label();

        labelOne.setName("Chicken");
        labelTwo.setName("Dog");
        labelThree.setName("Goose");

        allLabels.add(labelOne);
        allLabels.add(labelTwo);
        allLabels.add(labelThree);

        assertTrue(verifier.searchForLabel(allLabels, "Dog"));
    }
}
