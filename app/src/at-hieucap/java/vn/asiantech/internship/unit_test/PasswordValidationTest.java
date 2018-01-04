package vn.asiantech.internship.unit_test;

import java.util.regex.Pattern;

/**
 * Create check password
 * Created by tiboo on 03/01/2018.
 */
public final class PasswordValidationTest {
    public PasswordValidationTest() {
    }

    public static boolean isDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    public static boolean checkCharacterSpecialAndDigitNumber(String password) {
        return password.matches("[a-zA-Z.? ]*") || (Pattern.compile("[0-9 ]")).
                matcher(password).find();
    }

    public static boolean checkLength(String password) {
        return password.length() > 7;
    }

    public static boolean checkSameCharacter(String password) {
        int count = 0;
        String[] text = password.split("");
        for (int i = 0; i < text.length; i++) {
            for (int j = i + 1; j < text.length; j++) {
                if (text[i].equals(text[j])) {
                    count++;
                }
            }
        }
        return count <= 2;
    }

    public static boolean isHaveSpace(String password) {
        return !password.contains(" ");
    }

    public static boolean checkCapitalLetter(String password) {
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                numCount++;
            }
        }
        return numCount >= 3;
    }
}
