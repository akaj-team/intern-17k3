package vn.asiantech.internship.models;

public class User {
    private int imgAvatar;
    private String mail;

    public User(int imgAvatar, String mail) {
        this.imgAvatar = imgAvatar;
        this.mail = mail;
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public String getMail() {
        return mail;
    }
}
