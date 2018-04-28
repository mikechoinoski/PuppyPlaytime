package com.choinoski.persistence;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberSearchCriteria {

    private int    minimumAge;
    private int    maximumAge;
    private String minimumSize;
    private String maximumSize;
    private String gender;
    private String intact;

}
