package com.choinoski.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class MemberSearchCriteria {

    private int    minimumAge;
    private int    maximumAge;
    private String minimumSize;
    private String maximumSize;
    private String gender;
    private String fixed;

}
