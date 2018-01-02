package vn.asiantech.internship.ui.unittest;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserValidation is validate for edit text username
 */
public final class UserValidation {

    public static boolean checkLength(String username) {
        return username.length() > 5 || username.length() < 24;
    }

}
