package vn.asiantech.internship.ui.validation;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/01/2018.
 */

public final class PasswordValidation {

    private PasswordValidation() {
    }

    /**
     * Check the username must be different than the password
     */
    public static boolean isCheckPasswordDifferentUser(String userName, String passWord) {
        return !passWord.equals(userName);
    }

    /**
     * Check password must be special
     */
    public static boolean isCheckPassSpecial(String password) {
        return !password.matches("([a-zA-Z].+)([!@#$%^&*].+)");
    }

    /**
     * Check lenght password
     */
    public static boolean isCheckPassLenght(String password) {
        return password.length() > 7;
    }

    /**
     * Check password not repeat
     */
    public static boolean isCheckPassRepeat(String password) {
        int numCount = 0;
        String[] words = password.split("");
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    numCount++;
                }
            }
            if (numCount > 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check password not character space
     */
    public static boolean isPasswordSpace(String password) {
        return !password.contains(" ");
    }

    /**
     * Check password capital
     */
    public static boolean isCheckPassCapital(String password) {
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                numCount++;
                if (numCount >= 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
