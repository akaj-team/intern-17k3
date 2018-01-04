package vn.asiantech.internship.ui.unittest;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/01/2018.
 */

public class UserValidation {
    public UserValidation() {
    }

    public static boolean checkUserNameLenght(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    public static boolean checkCapUserName(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
