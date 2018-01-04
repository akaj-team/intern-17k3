package vn.asiantech.internship.validation;

import java.util.regex.Pattern;

/**
 * Created by anh.quach on 1/2/18.
 * Password Validation
 */
public class PasswordValidation {
    private PasswordValidation() {

    }

    public static boolean isDifferentUserName(String pass, String userName) {
        return !pass.equals(userName);
    }

    public static boolean isSpecialCharOrNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() || digitCasePatten.matcher(password).find();
    }

    public static boolean isPasswordLenght(String pass) {
        if (pass.length() >= 7) {
            int numCount = 0;
            String[] words = pass.split("");
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (words[i].equals(words[j])) {
                        numCount++;
                        if (numCount > 2) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isPassSpace(String pass) {
        return !pass.contains(" ");
    }

    public static boolean isMostThreeCapitalChar(String pass) {
        int count = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))) {
                count++;
            }
        }
        return count <= 3;
    }
}
