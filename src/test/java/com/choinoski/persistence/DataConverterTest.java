package com.choinoski.persistence;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Properties;
import static org.h2.util.SortedProperties.loadProperties;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Data Conversion class.
 *
 * @author mchoinoski
 */
public class DataConverterTest {

    private Properties properties;
    DataConverter converter;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        try {
            properties = loadProperties("/home/student/IdeaProjects/PuppyPlaytime/src/test/resources/puppyPlaytime.properties");
            converter = new DataConverter(properties);
        } catch (IOException e) {
            Assert.fail();
        }

    }

    /**
     * Verify the minimum weight for XS
     */
    @Test
    void testGetMinimumWeightForSizeXS() {
        int weight = converter.getMinimumWeightForSize("XS");
        assertEquals(0, weight);
    }

    /**
     * Verify the minimum weight for S
     */
    @Test
    void testGetMinimumWeightForSizeS() {
        int weight = converter.getMinimumWeightForSize("S");
        assertEquals(10, weight);
    }

    /**
     * Verify the minimum weight for M
     */
    @Test
    void testGetMinimumWeightForSizeM() {
        int weight = converter.getMinimumWeightForSize("M");
        assertEquals(25, weight);
    }

    /**
     * Verify the minimum weight for L
     */
    @Test
    void testGetMinimumWeightForSizeL() {
        int weight = converter.getMinimumWeightForSize("L");
        assertEquals(75, weight);
    }

    /**
     * Verify the minimum weight for XL
     */
    @Test
    void testGetMinimumWeightForSizeXL() {
        int weight = converter.getMinimumWeightForSize("XL");
        assertEquals(130, weight);
    }

    /**
     * Verify the minimum weight for XS
     */
    @Test
    void testGetMaximumWeightForSizeXS() {
        int weight = converter.getMaximumWeightForSize("XS");
        assertEquals(9, weight);
    }

    /**
     * Verify the minimum weight for S
     */
    @Test
    void testGetMaximumWeightForSizeS() {
        int weight = converter.getMaximumWeightForSize("S");
        assertEquals(24, weight);
    }

    /**
     * Verify the minimum weight for M
     */
    @Test
    void testGetMaximumWeightForSizeM() {
        int weight = converter.getMaximumWeightForSize("M");
        assertEquals(74, weight);
    }

    /**
     * Verify the minimum weight for L
     */
    @Test
    void testGetMaximumWeightForSizeL() {
        int weight = converter.getMaximumWeightForSize("L");
        assertEquals(129, weight);
    }

    /**
     * Verify the minimum weight for XL
     */
    @Test
    void testGetMaximumWeightForSizeXL() {
        int weight = converter.getMaximumWeightForSize("XL");
        assertEquals(300, weight);
    }

    /**
     * Verify the method to convert fixed to intact
     */
    @Test
    void testGetIntactTrueFromFixed() {
        Boolean intact = converter.getIntact("No");
        assertEquals(true, intact);
    }

    /**
     * Verify the method to convert fixed to intact
     */
    @Test
    void testGetIntactFalseFromFixed() {
        Boolean intact = converter.getIntact("Yes");
        assertEquals(false, intact);
    }

    /**
     * Verify the method to convert a string to a char for male
     */
    @Test
    void testGetCharGenderMale() {
        char gender = converter.getCharGender("Male");
        assertEquals('M', gender);
    }

    /**
     * Verify the method to convert a string to a char for female
     */
    @Test
    void testGetCharGenderFemale() {
        char gender = converter.getCharGender("Female");
        assertEquals('F', gender);
    }

    /**
     * Test the convert of a String for Intact to boolean when it's Yes
     */
    @Test
    void testConvertIntactYes() {
        boolean intact = converter.convertIntact("Yes");
        assertEquals(true, intact);
    }

    /**
     * Test the convert of a String for Intact to boolean when it's No
     */
    @Test
    void testConvertIntactNo() {
        boolean intact = converter.convertIntact("No");
        assertEquals(false, intact);
    }
}
