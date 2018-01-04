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
     * @param userName username
     * @return true when length of username > 5 and < 24 char
     */
    public static boolean isValidateLengthUserName(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    /**
     * check user name have least one capital letter and most 2 number
     *
     * @param userName username
     * @return true when username have least one capital letter and most 2 number
     */
    public static boolean isCapitalLetterAndNumber(String userName) {
        int numCount = 0;
        boolean capitalFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                capitalFlag = true;
            } else if (Character.isDigit(userName.charAt(i))) {
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
     * @param userName username
     * @return true when username not have space
     */
    public static boolean isUserNameSpace(String userName) {
        return !userName.contains(" ");
    }

    /**
     * check user name have special char
     *
     * @param userName username
     * @return when username not have special char
     */
    public static boolean isUserNameSpecialChar(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }

    /**
     * Check no distinction between uppercase and lowercase letters
     *
     * @param userName username
     * @return true when no distinction between uppercase and lowercase letters
     */
    public static boolean isUserNameIgnoreUpperCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
