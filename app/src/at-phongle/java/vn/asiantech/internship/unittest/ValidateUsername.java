package vn.asiantech.internship.unittest;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phongle on 3/1/2561.
 * Class check validate username
 */
public final class ValidateUsername {
    /**
     * Check length username more than 5 and little than 24
     *
     * @param username username
     * @return checkLengthUsername boolean state
     */
    public static boolean checkLengthUsername(String username) {
        return (5 < username.length()) && (username.length() < 24);
    }

    /**
     * Check at least uppercase in username
     *
     * @param username username
     * @return isAtLeastUpperCase boolean state
     */
    public static boolean isAtLeastUpperCase(String username) {
        for (int i = 0; i < username.length(); i++) {
            if (Character.isUpperCase(username.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check non special char in username
     *
     * @param username username
     * @return isNonSpecialChar boolean state
     */
    public static boolean isNonSpecialChar(String username) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        return !(m.find());
    }

    /**
     * Check non white space in username
     *
     * @param username username
     * @return isNonWhiteSpace boolean state
     */
    public static boolean isNonWhiteSpace(String username) {
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check at most two number in username
     *
     * @param username username
     * @return isAtMostTwoNumber boolean state
     */
    public static boolean isAtMostTwoNumber(String username) {
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            if (Character.isDigit(username.charAt(i))) {
                count++;
            }
        }
        return count <= 2;
    }

    /**
     * Function non distinct uppercase and lowercase
     *
     * @param username
     * @return isUserUpperCaseOrLowercase boolean state
     */
    public static boolean isUserNameUpperCaseOrLowerCase(String username) {
        return !username.equals(username.toLowerCase(Locale.getDefault()));
    }
}
