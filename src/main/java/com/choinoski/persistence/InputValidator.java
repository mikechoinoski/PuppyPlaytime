package com.choinoski.persistence;

import java.util.regex.Pattern;
/**
 *  This servlet creates a new pack from the information provided by a form. If the pack
 *  is successully create, the user is logged into the yourPack page.
 *
 * Sources:
 *
 * Regex Strings based on the following threads:
 *
 * https://stackoverflow.com/questions/25763935/how-to-check-phone-number-format-is-valid-or-not-from-telephony-manager
 * https://stackoverflow.com/questions/8204680/java-regex-email
 * https://stackoverflow.com/questions/33467536/how-to-check-if-a-string-is-made-only-of-letters-and-numbers
 * https://stackoverflow.com/questions/7939913/validating-string-input-has-no-numbers
 *
 * @author mrchoinoski
 */


public class InputValidator {

    private static Pattern nameValidationText  = Pattern.compile("^[A-Za-z]+$");
    private static Pattern addressValidationText  = Pattern.compile("[a-zA-Z0-9]*");
    private static Pattern phoneValidationText = Pattern.compile("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$");
    private static Pattern emailValidationText = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    private static Pattern passwordValidationText = Pattern.compile("(?=.{8,})");

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
