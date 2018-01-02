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
     * @param userName
     * @return
     */
    public static boolean checkLengthUserName(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    /**
     * check user name have least one capital letter and most 2 number
     *
     * @param userName
     * @return
     */
    public static boolean checkCapitalLetterAndNumber(String userName) {
        char ch;
        int numCount = 0;
        boolean capitalFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < userName.length(); i++) {
            ch = userName.charAt(i);
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
     * @param userName
     * @return
     */
    public static boolean checkUserNameSpace(String userName) {
        return !userName.contains(" ");
    }

    /**
     * check user name have special char
     *
     * @param userName
     * @return
     */
    public  static boolean checkUserNameSpecialChar(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }

    /**
     * Check no distinction between uppercase and lowercase letters
     *
     * @param userName
     * @return
     */
    public static boolean checkUserNameIgnoreUpperCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
