package com.choinoski.persistence;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Data validator.
 */
public class DataValidator {

    /**
     * Validate form data when adding a member
     *
     * @param memberName the member name
     * @param breed      the breed
     * @param birthday   the birthday
     * @return the array list
     */
    public ArrayList<String> validateFormMemberData(String memberName, String breed, LocalDate birthday) {

        ArrayList<String> errorList = new ArrayList();

        if (!InputValidator.nameValidation(memberName)) {
            errorList.add("Invalid Member Name");
        }

        if (!InputValidator.nameValidation(breed)) {
            errorList.add("Invalid Breed");
        }

        if (birthday.isBefore(LocalDate.now())) {
            errorList.add("Invalid Birthdate");
        }

        return errorList;

    }

    /**
     * Validate form data when adding a pack
     *
     * @param packName     the pack name
     * @param firstName    the first name
     * @param lastName     the last name
     * @param address      the address
     * @param phoneNumber  the phone number
     * @param emailAddress the email address
     * @param password     the password
     * @return the array list
     */
    public ArrayList<String> validateFormPackData(String packName, String firstName, String lastName,
                                     String address, String phoneNumber, String emailAddress, String password) {

        ArrayList<String> errorList = new ArrayList();

        if (!InputValidator.nameValidation(packName)) {
            errorList.add("Invalid PackName");
        }
        if (!InputValidator.nameValidation(firstName)) {
            errorList.add("Invalid First Name");
        }
        if (!InputValidator.nameValidation(lastName)) {
            errorList.add("Invalid Last Name");
        }
        if (!InputValidator.addressValidation(address)) {
            errorList.add("Invalid Address");
        }
        if (!InputValidator.phoneValidation(phoneNumber)) {
            errorList.add("Invalid Phone Number");
        }
        if (!InputValidator.emailValidation( emailAddress)) {
            errorList.add("Invalid Email Address");
        }
        if (!InputValidator.passwordValidation(password)) {
            errorList.add("Invalid Password");
        }

        return errorList;

    }

}
