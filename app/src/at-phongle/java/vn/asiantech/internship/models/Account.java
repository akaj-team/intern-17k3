package vn.asiantech.internship.models;

/**
 * Created by phongle on 3/1/2561.
 * Class model account
 */
public class Account {
    private String Username;
    private String Password;

    public Account(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
