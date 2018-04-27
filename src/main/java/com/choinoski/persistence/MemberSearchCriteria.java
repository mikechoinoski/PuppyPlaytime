package com.choinoski.persistence;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberSearchCriteria {

    private LocalDate minimumBirthday;
    private LocalDate maximumBirthday;
    private int       minimumWeight;
    private int       maximumWeight;
    private String    gender;
    private String    intact;


}
