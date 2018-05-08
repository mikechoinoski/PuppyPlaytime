package com.choinoski.persistence;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataValidator {

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
