package vn.asiantech.internship.unittest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phongle on 3/1/2561.
 * Class validate password
 */
public final class ValidatePassword {
    private ValidatePassword() {
        // No - op
    }

    /**
     * Check password difference username
     *
     * @param username username
     * @param password password
     * @return isDifferenceUsername boolean state
     */
    public static boolean isDifferenceUsername(String username, String password) {
        return !password.equals(username);
    }

    /**
     * Check length password  > 7
     *
     * @param password password
     * @return boolean state
     */
    public static boolean isCheckMinLength(String password) {
        return password.length() >= 7;
    }

    /**
     * Check at least special char or number in password
     *
     * @param password password
     * @return atLeastSpecialCharOrNumber boolean state
     */
    public static boolean isAtLeastSpecialCharOrNumber(String password) {
        Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        return (m.find());
    }

    /**
     * Check non repeat twice in password
     *
     * @param password password
     * @return isNonRepeat boolean state
     */
    public static boolean isNonRepeat(String password) {
        String pass[] = password.split("");
        for (String i : pass) {
            int count = 0;
            for (String j : pass) {
                if (i.equalsIgnoreCase(j)) {
                    count++;
                }
            }
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check non white space in  password
     *
     * @param password password
     * @return isNonSpace boolean state
     */
    public static boolean isNonWhiteSpace(String password) {
        return !password.contains(" ");
    }

    /**
     * Check at least three uppercase in password
     *
     * @param password password
     * @return isAtLeastThreeUppercase boolean state
     */
    public static boolean isAtLeastThreeUpperCase(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                count++;
            }
        }
        return count >= 3;
    }
}
