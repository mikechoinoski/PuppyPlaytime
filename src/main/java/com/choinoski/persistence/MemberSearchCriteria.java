package com.choinoski.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberSearchCriteria {

    private int    minimumAge;
    private int    maximumAge;
    private String minimumSize;
    private String maximumSize;
    private String gender;
    private String fixed;

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

}
