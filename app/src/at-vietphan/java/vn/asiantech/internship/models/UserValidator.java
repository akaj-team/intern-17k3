package vn.asiantech.internship.models;

/**
 * Created by vietphan on 02/01/2018.
 * Class UserValidator
 */
public class UserValidator {
    private String UserName;
    private String Password;

    public UserValidator(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
