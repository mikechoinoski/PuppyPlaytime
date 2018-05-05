package com.choinoski.persistence;

public class DataConverter {

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
            minimumWeightForSize = 10;
        } else if (size.equals("M")) {
            minimumWeightForSize = 25;
        } else if (size.equals("L")) {
            minimumWeightForSize = 75;
        } else if (size.equals("XL")){
            minimumWeightForSize = 130;
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
            maximumWeightForSize = 9;
        } else if (size.equals("S")) {
            maximumWeightForSize = 24;
        } else if (size.equals("M")) {
            maximumWeightForSize = 74;
        } else if (size.equals("L")) {
            maximumWeightForSize = 129;
        } else if (size.equals("XL")){
            maximumWeightForSize = 300;
        }

        return maximumWeightForSize;
    }

    public char getCharGender(String genderData) {

        char gender = ' ';

        if (genderData.equals("Male")) {
            gender = 'M';
        } else if (genderData.equals("Female")) {
            gender = 'F';
        }

        return gender;

    }

    public Boolean getIntact(String fixedData) {

        Boolean intact = null;

        if (fixedData.equals("Yes")) {
            intact = false;
        } else if (fixedData.equals("No")) {
            intact = true;
        }

        return intact;

    }

    public boolean convertIntact(String intactData) {

        boolean memberIntact = false;
        if (intactData.equals("Yes")) {
            memberIntact = true;
        }

        return memberIntact;

    }

}
