package vn.asiantech.internship.ui.unittest;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserTesting is validate for edit text username
 */
public final class UserValidation {

    /**
     * is length input username more than 5 and a little than 24
     *
     * @param userName userName
     * @return isLengthUserName
     */
    public static boolean isLengthUserName(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    /**
     * is username have at least one capital letter
     *
     * @param userName userName
     * @return isLeastOneCapitalLetterUserName boolean state
     */
    public static boolean isLeastOneCapitalLetterUserName(String userName) {
        boolean isCapital = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                isCapital = true;
            }
            if (isCapital) {
                return true;
            }
        }
        return false;
    }

    /**
     * is username does not contain special characters and spaces
     *
     * @param userName userName
     * @return isSpecialCharAndSpaceUserName boolean state
     */
    public static boolean isSpecialCharAndSpaceUserName(String userName) {
        return userName.matches("[a-zA-Z0-9.?]*");
    }

    /**
     * is username have most 2 number
     *
     * @param userName userName
     * @return isMostTwoNumberUserName boolean state
     */
    public static boolean isMostTwoNumberUserName(String userName) {
        int numCount = 0;
        boolean isSumNumber = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                numCount++;
            }
        }
        if (numCount <= 2) {
            isSumNumber = true;
        }
        return isSumNumber;
    }

    /**
     * is no distinction between uppercase and lowercase username
     *
     * @param userName userName
     * @return isUpperCaseLowercaseUserName boolean state
     */
    public static boolean isUpperCaseLowercaseUserName(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }

    /**
     * is password different username
     *
     * @param userName userName
     * @param password password
     * @return isPasswordDifferentUserName boolean state
     */
    public static boolean isPasswordDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    /**
     * is password have at least 1 special char or 1 number
     *
     * @param password password
     * @return isAtLeastSpecialOrNumber boolean state
     */
    public static boolean isAtLeastSpecialOrNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() || digitCasePatten.matcher(password).find();
    }

    /**
     * is length password more than 7
     *
     * @param password password
     * @return isLengthPassword boolean state
     */
    public static boolean isLengthPassword(String password) {
        return password.length() > 7;
    }

    /**
     * is repeat Character 2 times
     *
     * @param password password
     * @return isRepeat boolean state
     */
    public static boolean isRepeatCharacterPassword(String password) {
        boolean isRepeat = true;
        int numCount = 0;
        String[] strings = password.split("");
        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[i].equalsIgnoreCase(strings[j])) {
                    numCount++;
                }
            }
            if (numCount > 2) {
                isRepeat = false;
                numCount = 0;
            }
        }
        return isRepeat;
    }

    /**
     * is space input password
     *
     * @param password password
     * @return isSpacePassword boolean state
     */
    public static boolean isSpacePassword(String password) {
        return !password.contains(" ");
    }

    /**
     * is input password have at least 3 Characters
     *
     * @param password password
     * @return isSumCharacters boolean state
     */
    public static boolean isLestThreeCharacters(String password) {
        int numCount = 0;
        boolean isSumCharacters = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                numCount++;
            }
        }
        if (numCount >= 3) {
            isSumCharacters = true;
        }
        return isSumCharacters;
    }
}
