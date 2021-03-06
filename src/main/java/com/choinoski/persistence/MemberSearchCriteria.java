package com.choinoski.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Member search criteria.
 *
  @author mchoinoski
 */
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

}
