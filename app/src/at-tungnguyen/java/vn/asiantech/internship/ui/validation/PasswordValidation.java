package vn.asiantech.internship.ui.unittest;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/01/2018.
 */

public class PasswordValidation {
    public PasswordValidation() {
    }
    public static boolean checkPasswordLenght(String userName,String passWord){
        return !passWord.equals(userName);
    }
}
