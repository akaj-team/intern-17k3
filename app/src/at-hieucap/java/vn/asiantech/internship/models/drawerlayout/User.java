package vn.asiantech.internship.models.drawerlayout;

import android.graphics.drawable.Drawable;


public class User {
    private Drawable avatar;
    private String email;

    public User(Drawable avatar, String email) {
        this.avatar = avatar;
        this.email = email;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }
}
