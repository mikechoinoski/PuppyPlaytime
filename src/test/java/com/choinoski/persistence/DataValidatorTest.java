package com.choinoski.persistence;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Data validator test.
 */
class DataValidatorTest {

    DataValidator validator = null;

    /**
     * Verify the list size when all parameters are incorrect
     */
    @Test
    void testValidateFormMemberDataFailure() {

        ArrayList errors = null;

        validator = new DataValidator();
        errors = validator.validateFormMemberData("5555", "424", LocalDate.now().plusDays(6));
        assertEquals(errors.size(), 3);

    }

    /**
     * Verify the list size when all parameters are correct
     */
    @Test
    void testValidateFormMemberDataSuccess() {

        ArrayList errors = null;

        validator = new DataValidator();
        errors = validator.validateFormMemberData("Scout",
                "Golden Retriever", LocalDate.now().minusYears(10));
        assertEquals(errors.size(), 0);

    }

    /**
     * Verify the list size when all parameters are correct
     */
    @Test
    void testValidateFormPackDataFailure() {

        ArrayList errors = null;

        validator = new DataValidator();
        errors = validator.validateFormPackData("555",
                "555", "5555", "122","444","5553","58855");
        assertEquals(errors.size(), 7);

    }

    /**
     * Verify the list size when all parameters are correct
     */
    @Test
    void testValidateFormPackDataSuccess() {

        ArrayList errors = null;

        validator = new DataValidator();
        errors = validator.validateFormPackData("NewPack",
                "Mark", "Williams", "122 State Street","4144129911",
                "newgmail@yahoo.com","DownTown432");
        assertEquals(errors.size(), 0);

    }


}