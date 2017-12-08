package vn.asiantech.internship.models;

public class User {
    private int avatar;
    private String email;
    private String uri;

    public User(int avatar, String email, String uri) {
        this.avatar = avatar;
        this.email = email;
        this.uri = uri;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
