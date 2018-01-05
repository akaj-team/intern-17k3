package vn.asiantech.internship.ui.validation;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/01/2018.
 */

public final class UserValidation {
    private UserValidation() {
    }

    /**
     * Check the lenght UserName
     *
     * @param userName
     * @return
     */
    public static boolean ischeckUserLenght(String userName) {
        return userName.length() > 5 && userName.length() < 24;
    }

    /**
     * Check special UserName
     *
     * @param userName
     * @return
     */
    public static boolean isCheckUserSpecial(String userName) {
        Pattern checkUserSpecial = Pattern.compile("^[a-z0-9A-Z]");
        return checkUserSpecial.matcher(userName).find();
    }

    /**
     * Check Space of Username
     *
     * @param userName
     * @return
     */
    public static boolean isCheckUserSpace(String userName) {
        return !userName.contains(" ");
    }

    /**
     * Check capital and number
     *
     * @param userName
     * @return
     */
    public static boolean isCheckUserCapitalAndNumber(String userName) {
        boolean capitalCharacter = false;
        boolean numberCharacter = false;
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isUpperCase(userName.charAt(i))) {
                capitalCharacter = true;
            } else if (Character.isDigit(userName.charAt(i))) {

                if (i >= 2) {
                    numberCharacter = true;
                }
                if (capitalCharacter && numberCharacter) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check distinction between uppercase and lowercase letters
     *
     * @param userName
     * @return
     */
    public static boolean isUserNameUpperCaseOrLowerCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
}
