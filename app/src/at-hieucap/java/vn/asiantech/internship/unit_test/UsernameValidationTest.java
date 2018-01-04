package vn.asiantech.internship.unit_test;

import java.util.Locale;

/**
 * Create check user name
 * Created by tiboo on 03/01/2018.
 */
public final class UsernameValidationTest {
    public static boolean checkLength(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    public static boolean checkCapitalLetter(String userName) {
        boolean capitalLetterFlag = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                capitalLetterFlag = true;
            }
        }
        return capitalLetterFlag;
    }

    public static boolean checkHaveSpace(String userName) {
        return !userName.contains(" ");
    }

    public static boolean checkCharacters(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }

    public static boolean checkDigitNumber(String userName) {
        boolean digitNumberFlag = false;
        int countDigitNumber = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                countDigitNumber++;
                digitNumberFlag = countDigitNumber <= 2;
            }
        }
        return digitNumberFlag;
    }

    public static boolean checkUpperCaseOrLowerCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
