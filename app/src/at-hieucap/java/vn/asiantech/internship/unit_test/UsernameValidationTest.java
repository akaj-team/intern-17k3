package vn.asiantech.internship.unit_test;

import java.util.Locale;

/**
 * Create check user name
 * Created by tiboo on 03/01/2018.
 */
public final class UsernameValidationTest {
    private UsernameValidationTest() {
        // No-oop
    }

    public static boolean isLengthAllowed(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    public static boolean isCapitalLetter(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHaveSpace(String userName) {
        return !userName.contains(" ");
    }

    public static boolean isCharacters(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }

    public static boolean isDigitNumber(String userName) {
        int countDigitNumber = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                countDigitNumber++;
            }
        }
        return countDigitNumber <= 2;
    }

    public static boolean isUpperCaseOrLowerCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
