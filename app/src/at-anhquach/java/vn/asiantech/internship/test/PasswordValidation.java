package vn.asiantech.internship.test;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by anh.quach on 1/2/18.
 * Password Validation
 */
public class PasswordValidation {
    public static boolean checkDifferentUserName(String pass, String userName){
        return !pass.equals(userName);
    }
    public static boolean checkUserNameUpperCase(String userName) {
        return !userName.equals(userName.toLowerCase(Locale.getDefault()));
    }
    public static boolean checkSpecialCharOrNumber(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return specialCharPatten.matcher(password).find() && digitCasePatten.matcher(password).find();
    }
    public static boolean checkPasswordLenght(String pass){
        if (pass.length()>=7){
            String str = pass.substring(0,7);
        }
    }
    public static boolean checkUserNameSpace(String userName) {
        return !userName.contains(" ");
    }
}
