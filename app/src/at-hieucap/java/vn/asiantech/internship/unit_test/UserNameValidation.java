package vn.asiantech.internship.unit_test;

import java.util.Locale;

/**
 * Create check user name
 * Created by tiboo on 03/01/2018.
 */
public final class UserNameValidation {
    public static boolean checkLength(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    public static boolean checkCapitalLetter(String userName) {
        char ch;
        boolean capitalLetterFlag = false;
        for (int i = 0; i < userName.length(); i++) {
            ch = userName.charAt(i);
            if (Character.isUpperCase(ch)) {
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
        char ch;
        boolean digitNumberFlag = false;
        int countDigitNumber = 0;
        for (int i = 0; i < userName.length(); i++) {
            ch = userName.charAt(i);
            if (Character.isDigit(ch)) {
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
