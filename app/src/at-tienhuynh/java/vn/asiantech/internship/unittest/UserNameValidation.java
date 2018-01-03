package vn.asiantech.internship.unittest;

import java.util.Locale;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
public final class UserNameValidation {

    /**
     * check length userName > 5 and < 24 character
     *
     * @param username username
     * @return true when length of username > 5 and < 24 char
     */
    public static boolean isLengthUserName(String username) {
        return username.length() > 5 && username.length() < 24;
    }

    /**
     * check user name have least one capital letter and most 2 number
     *
     * @param username username
     * @return true when username have least one capital letter and most 2 number
     */
    public static boolean isCapitalLetterAndNumber(String username) {
        char ch;
        int numCount = 0;
        boolean capitalFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < username.length(); i++) {
            ch = username.charAt(i);
            if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isDigit(ch)) {
                numCount++;
                if (numCount <= 2) {
                    numberFlag = true;
                }
            }
            if (capitalFlag && numberFlag) {
                return true;
            }
        }
        return false;
    }

    /**
     * check user name have space
     *
     * @param username username
     * @return true when username not have space
     */
    public static boolean isUserNameSpace(String username) {
        return !username.contains(" ");
    }

    /**
     * check user name have special char
     *
     * @param username username
     * @return when username not have special char
     */
    public static boolean isUserNameSpecialChar(String username) {
        return username.matches("[A-Za-z0-9]+");
    }

    /**
     * Check no distinction between uppercase and lowercase letters
     *
     * @param username username
     * @return true when no distinction between uppercase and lowercase letters
     */
    public static boolean isUserNameIgnoreUpperCase(String username) {
        return !username.equals(username.toLowerCase(Locale.getDefault()));
    }
}
