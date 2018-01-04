package vn.asiantech.internship.unit_test;

import java.util.regex.Pattern;

/**
 * Create check password
 * Created by tiboo on 03/01/2018.
 */
public final class PasswordValidation {
    public static boolean checkDifferentUserName(String userName, String password) {
        return !password.equals(userName);
    }

    public static boolean checkCharacterSpecialAndDigitNumber(String password) {
        Boolean isCharacterSpecial = password.matches("[a-zA-Z.? ]*");
        Boolean isDigitNumber = (Pattern.compile("[0-9 ]")).matcher(password).find();
        return isCharacterSpecial || isDigitNumber;
    }

    public static boolean checkLength(String password) {
        return password.length() > 7;
    }

    public static boolean checkSameCharacter(String password) {
        boolean isSame = false;
        int count = 0;
        String[] text = password.split("");
        for (int i = 0; i < text.length; i++) {
            for (int j = i + 1; j < text.length; j++) {
                if (text[i].equals(text[j])) {
                    count++;
                    if (count <= 2) {
                        isSame = true;
                    }
                }
            }
        }
        return isSame;
    }

    public static boolean checkHaveSpace(String password) {
        return !password.contains(" ");
    }

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
}
