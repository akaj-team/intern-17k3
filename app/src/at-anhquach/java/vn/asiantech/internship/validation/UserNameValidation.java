package vn.asiantech.internship.validation;

import java.util.Locale;

/**
 * Created by anh.quach on 1/2/18.
 * User name validation
 */
public class UserNameValidation {
    private UserNameValidation() {

    }

    public static boolean checkUserNameLength(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    public static boolean checkCapitalUserName(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserNameSpace(String userName) {
        return !userName.contains(" ");
    }

    public static boolean checkContainSpecialChar(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }

    public static boolean checkMostTwoDigits(String userName) {
        int count = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isDigit(userName.charAt(i))) {
                count++;
            }
        }
        return count <= 2;
    }

    public static boolean checkUserNameIgnoreUpperCase(String userName) {
        return userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
