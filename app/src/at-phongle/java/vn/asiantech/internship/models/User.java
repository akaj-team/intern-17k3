package vn.asiantech.internship.models;

public class User {
    private int avatar;
    private String mail;

    public User(int avatar, String mail) {
        this.avatar = avatar;
        this.mail = mail;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
