package com.choinoski.persistence;

import java.util.regex.Pattern;
/**
 *  This class validates different types of data with Regex
 *
 * Sources:
 *
 * Regex Strings based on the following threads:
 *
 * https://stackoverflow.com/questions/25763935/how-to-check-phone-number-format-is-valid-or-not-from-telephony-manager
 * http://emailregex.com/
 * https://stackoverflow.com/questions/33467536/how-to-check-if-a-string-is-made-only-of-letters-and-numbers
 * https://stackoverflow.com/questions/7939913/validating-string-input-has-no-numbers
 * https://www.codeproject.com/Tips/989012/Validate-and-Find-Addresses-with-RegEx
 *
 * @author mrchoinoski
 */


public class InputValidator {

    private static Pattern nameValidationText  = Pattern.compile("^[A-Za-z]+$");
    private static Pattern addressValidationText  = Pattern.compile("\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?");
    private static Pattern phoneValidationText = Pattern.compile("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$");
    private static Pattern emailValidationText = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private static Pattern passwordValidationText = Pattern.compile("^[a-zA-Z]\\w{3,14}$");

    public static boolean nameValidation(String s) {
        return nameValidationText.matcher(s).matches();
    }

    public static boolean addressValidation(String s) {
        return addressValidationText.matcher(s).matches();
    }

    public static boolean emailValidation(String s) {
        return emailValidationText.matcher(s).matches();
    }

    public static boolean phoneValidation(String s) {
        return phoneValidationText.matcher(s).matches();
    }

    public static boolean passwordValidation(String s) {
        return passwordValidationText.matcher(s).matches();
    }

}
