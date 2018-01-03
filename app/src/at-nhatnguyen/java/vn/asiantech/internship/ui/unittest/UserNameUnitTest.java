package vn.asiantech.internship.ui.unittest;

/**
 * Created by hoangnhat on 03/01/2018.
 * class test UI
 */
public final class UserNameUnitTest {

    public static boolean checkLength(String userName) {
        return userName.length() < 5 || userName.length() >= 24;
    }

    public static boolean checkUpperCase(String userName){
        for(int i = 0;i<userName.length();i++){
            if(Character.isUpperCase(userName.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
