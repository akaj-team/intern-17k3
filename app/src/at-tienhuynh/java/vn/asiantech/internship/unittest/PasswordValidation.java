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
     * @param userName
     * @param password
     * @return
     */
    public static boolean checkDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    /**
     * Check have at least one special character or number
     *
     * @param password
     * @return
     */
    public static boolean checkAtLeastNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() || digitCasePatten.matcher(password).find();
    }

    /**
     * check password have space
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordSpace(String password) {
        return !password.contains(" ");
    }

    /**
     * check password have 3 capital letter
     *
     * @param password
     * @return
     */
    public static boolean checkCapitalLetter(String password) {
        char character;
        int numCount = 0;
        boolean capitalFlag = false;
        for (int i = 0; i < password.length(); i++) {
            character = password.charAt(i);
            if (Character.isUpperCase(character)) {
                numCount++;
                if (numCount >= 3) {
                    capitalFlag = true;
                }
            }
        }
        return capitalFlag;
    }

    /**
     * Check length > 7
     *
     * @param password
     * @return
     */
    public static boolean checkLength(String password) {
        return password.length() > 7;
    }

    /**
     * Check repeat character password > 2 times
     *
     * @param password
     * @return
     */
    public static boolean checkRepeatCharacter(String password) {
        boolean isRepeat = false;
        int numCount = 0;
        String[] words = password.split("");
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    numCount++;
                    if (numCount > 2) {
                        isRepeat = true;
                    }
                }
            }
        }
        return !isRepeat;
    }
}
