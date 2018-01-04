package vn.asiantech.internship.ui.unittest;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by hoangnhat on 03/01/2018.
 * class test UI userName
 */
public final class ValidateUserName {
    /**
     * check length of userName
     *
     * @param userName user name
     * @return true if 5<userName<24
     */
    public static boolean isLengthUserName(String userName) {
        return !(userName.length() < 5 || userName.length() > 24);
    }

    /**
     * check upperCase in userName
     *
     * @param userName user name
     * @return true if userName have a upper case
     */
    public static boolean isUpperCaseUserName(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check user name have special case or space
     *
     * @param userName user name
     * @return true when userName have special case or space
     */
    public static boolean isSpecialCaseUserName(String userName) {
        Pattern specialCharPatten = Pattern.compile("[^a-zA-Z0-9]", Pattern.CASE_INSENSITIVE);
        return !specialCharPatten.matcher(userName).find();
    }

    /**
     * Check user name have 2 number
     *
     * @param userName user name
     * @return true when userName less than 2 number
     */
    public static boolean isNumberUserName(String userName) {
        int count = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                count++;
            }
        }
        return count <= 2;
    }

    /**
     * Check userName is upper case or lower case
     *
     * @param userName user name
     * @return usually true
     */
    public static boolean isUserNameUpperCaseOrLowerCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
