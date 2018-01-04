package vn.asiantech.internship.unittest;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
public class User {

    private String userName;
    private String password;

    public User(String user, String password) {
        this.userName = user;
        this.password = password;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
