package vn.asiantech.internship.ui.unittest;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserTesting is validate for edit text username
 */
@SuppressWarnings("ALL")
public final class UserValidation {

    /**
     * Check length input username more than 5 and a little than 24
     *
     * @param userName
     * @return
     */
    public static boolean checkLengthUserName(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    /**
     * Check username have at least one capital letter
     *
     * @param userName
     * @return
     */
    public static boolean checkLeastOneCapitalLetterUserName(String userName) {
        boolean checkCapital = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                checkCapital = true;
            }
            if (checkCapital) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check username does not contain special characters and spaces
     *
     * @param userName
     * @return
     */
    public static boolean checkSpecialCharAndSpaceUserName(String userName) {
        return userName.matches("[a-zA-Z0-9.? ]*");
    }

    /**
     * Check username have most 2 number
     *
     * @param userName
     * @return
     */
    public static boolean checkMostTwoNumberUserName(String userName) {
        int numCount = 0;
        boolean checkSumNumber = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                numCount++;
                if (numCount <= 2) {
                    checkSumNumber = true;
                }
            }
            if (checkSumNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check no distinction between uppercase and lowercase username
     *
     * @param userName
     * @return
     */
    public static boolean checkUpperCaseLowercaseUserName(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }

    /**
     * Check password different username
     *
     * @param userName
     * @param password
     * @return
     */
    public static boolean checkPasswordDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    /**
     * Check password have at least 1 special char or 1 number
     *
     * @param password
     * @return
     */
    public static boolean checkAtLeastSpecialOrNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() || digitCasePatten.matcher(password).find();
    }

    /**
     * Check length password more than 7
     *
     * @param password
     * @return
     */
    public static boolean checkLengthPassword(String password) {
        return password.length() > 7;
    }

    /**
     * Check repeat Character 2 times
     *
     * @param password
     * @return
     */
    public static boolean checkRepeatCharacter(String password) {
        boolean isRepeat = false;
        int numCount = 0;
        String[] strings = password.split("");
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[i].equals(strings[j])) {
                    numCount++;
                    if (numCount > 2) {
                        isRepeat = true;
                    }
                }
            }
        }
        return !isRepeat;
    }

    /**
     * Check space input password
     *
     * @param password
     * @return
     */
    public static boolean checkSpacePassword(String password) {
        return !password.contains(" ");
    }

    /**
     * Check input password have at least 3 Characters
     *
     * @param password
     * @return
     */
    public static boolean checkLestThreeCharacters(String password) {
        int numCount = 0;
        boolean checkSumCharacters = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                numCount++;
                if (numCount >= 3) {
                    checkSumCharacters = true;
                }
            }
        }
        return checkSumCharacters;
    }
}
