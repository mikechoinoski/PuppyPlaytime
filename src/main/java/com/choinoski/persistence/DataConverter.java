package com.choinoski.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.h2.util.SortedProperties.loadProperties;

/**
 * A class that converts entity information into other forms.
 *
 * @author mchoinoski
 */
public class DataConverter {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties;




    public DataConverter(Properties properties) {
        this.properties = properties;
    }

    /**
     * Gets the minimum weight for a size.
     *
     * @return the minimum weight for a size
     */
    public int getMinimumWeightForSize(String size) {
        int minimumWeightForSize = 0;
        if (size.equals("XS")) {
            minimumWeightForSize = 0;
        } else if (size.equals("S")) {
            minimumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.one.max")) + 1;
        } else if (size.equals("M")) {
            minimumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.two.max")) + 1;
        } else if (size.equals("L")) {
            minimumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.three.max")) + 1;
        } else if (size.equals("XL")){
            minimumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.four.max")) + 1;
        }
        return minimumWeightForSize;
    }
    /**
     * Gets the minimum weight for a size.
     *
     * @return the minimum weight for a size
     */
    public int getMaximumWeightForSize(String size) {
        int maximumWeightForSize = 0;
        if (size.equals("XS")) {
            maximumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.one.max"));
        } else if (size.equals("S")) {
            maximumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.two.max"));
        } else if (size.equals("M")) {
            maximumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.three.max"));
        } else if (size.equals("L")) {
            maximumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.four.max"));
        } else if (size.equals("XL")){
            maximumWeightForSize = Integer.parseInt(properties.getProperty("member.size.tier.five.max"));;
        }
        return maximumWeightForSize;
    }

    /**
     * Gets intact.
     *
     * @param fixedData the fixed data
     * @return the intact
     */
    public Boolean getIntact(String fixedData) {
        Boolean intact = null;
        if (fixedData.equals("Yes")) {
            intact = false;
        } else if (fixedData.equals("No")) {
            intact = true;
        }
        return intact;
    }

    /**
     * Convert weight to size string.
     *
     * @param weight the weight
     * @return the string
     */
    public String convertWeightToSize(int weight) {

        String size =  null;

        if (weight <= Integer.parseInt(properties.getProperty("member.size.tier.one.max"))) {
            size = "XS";
        } else if (weight <= Integer.parseInt(properties.getProperty("member.size.tier.two.max"))) {
            size = "S";
        } else if (weight <= Integer.parseInt(properties.getProperty("member.size.tier.three.max"))) {
            size = "M";
        } else if (weight <= Integer.parseInt(properties.getProperty("member.size.tier.four.max"))) {
            size = "L";
        } else {
            size = "XL";
        }

        return size;

    }

    /**
     * Gets gender in the form of a char.
     *
     * @param genderData the gender data
     * @return the char gender
     */
    public char getCharGender(String genderData) {

        char gender = ' ';

        if (genderData.equals("Male")) {
            gender = 'M';
        } else if (genderData.equals("Female")) {
            gender = 'F';
        }

        return gender;

    }

    /**
     * Convert String for intact to a boolean.
     *
     * @param intactData the intact data
     * @return the boolean
     */
    public boolean convertIntact(String intactData) {

        boolean memberIntact = false;
        if (intactData.equals("Yes")) {
            memberIntact = true;
        }

        return memberIntact;

    }

}
