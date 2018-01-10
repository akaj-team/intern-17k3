package vn.asiantech.internship.unittest;

import java.util.regex.Pattern;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
public final class PasswordValidation {

    /**
     * Check password different username
     *
     * @param userName username
     * @param password password
     * @return true when password not equals username
     */
    public static boolean isDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    /**
     * Check have at least one special character or number
     *
     * @param password password
     * @return true when password have one special char or number
     */
    public static boolean isAtLeastNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() || digitCasePatten.matcher(password).find();
    }

    /**
     * check password have space
     *
     * @param password password
     * @return true when password not have space
     */
    public static boolean isPasswordSpace(String password) {
        return !password.contains(" ");
    }

    /**
     * check password have 3 capital letter
     *
     * @param password password
     * @return true when password have 3 capital letter
     */
    public static boolean isCapitalLetter(String password) {
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

    /**
     * Check length more than 7
     *
     * @param password password
     * @return true when length of password more than 7
     */
    public static boolean isLength(String password) {
        return password.length() > 7;
    }

    /**
     * Check repeat character password more than 2 times
     *
     * @param password
     * @return true when password have character repeat again
     */
    public static boolean isRepeatCharacter(String password) {
        int numCount = 0;
        String[] words = password.split("");
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    numCount++;
                    if (numCount > 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
