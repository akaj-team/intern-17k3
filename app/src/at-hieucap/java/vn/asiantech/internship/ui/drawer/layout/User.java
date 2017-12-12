package vn.asiantech.internship.ui.drawer.layout;

import android.graphics.drawable.Drawable;


class User {
    private Drawable avatar;
    private String email;

    User(Drawable avatar, String email) {
        this.avatar = avatar;
        this.email = email;
    }

    Drawable getAvatar() {
        return avatar;
    }

    void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    String getEmail() {
        return email;
    }
}
