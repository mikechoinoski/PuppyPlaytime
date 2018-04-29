package com.choinoski.util;

public class SizeAndWeightConverter {

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
            minimumWeightForSize = 60;
        } else if (size.equals("XL")){
            minimumWeightForSize = 110;
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
            maximumWeightForSize = 59;
        } else if (size.equals("L")) {
            maximumWeightForSize = 109;
        } else if (size.equals("XL")){
            maximumWeightForSize = 300;
        }

        return maximumWeightForSize;
    }

}
