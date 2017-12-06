package vn.asiantech.internship.models;

import android.graphics.drawable.Drawable;

public class User {
    private Drawable avatar;
    private String mail;

    public User(Drawable avatar, String mail) {
        this.avatar = avatar;
        this.mail = mail;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
