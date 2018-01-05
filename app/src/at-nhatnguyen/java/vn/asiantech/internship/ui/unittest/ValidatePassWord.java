package vn.asiantech.internship.ui.unittest;

import java.util.regex.Pattern;

/**
 * Created by hoangnhat on 03/01/2018.
 * class test ui password
 */
public final class ValidatePassWord {
    /**
     * Check user name like password
     *
     * @param userName user name
     * @param password password
     * @return true when userName like passWord
     */
    public static boolean isPassWordLikeUserName(String userName, String password) {
        return userName.equals(password);
    }

    /**
     * Check password have number or special case
     *
     * @param password password
     * @return true when passWord haven't any number or special case
     */
    public static boolean isSpecialNumberPassWord(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-zA-Z]", Pattern.CASE_INSENSITIVE);
        return specialCharPatten.matcher(password).find();
    }

    /**
     * Check length of password
     *
     * @param password password
     * @return true when length of password more than 7
     */
    public static boolean isLengthPassWord(String password) {
        return password.length() >= 7;
    }

    /**
     * Check appear
     *
     * @param password password
     * @return true when case appear more 2 time
     */
    public static boolean isAppearCase(String password) {
        int count = 0;
        String pass[] = password.split("");
        for (int i = 0; i < password.length(); i++) {
            for (int j = i + 1; j < password.length(); j++) {
                if (pass[i].equals(pass[j])) {
                    count++;
                    if (count > 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check space in password
     *
     * @param password password
     * @return true when password contains " "
     */
    public static boolean isSpaceCase(String password) {
        return !password.contains(" ");
    }

    /**
     * Check appear of upper case
     *
     * @param password password
     * @return true when upper case appear more 3 time
     */
    public static boolean isLeastThreeUpperCase(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                count++;
            }
        }
        return count >= 3;
    }
}
