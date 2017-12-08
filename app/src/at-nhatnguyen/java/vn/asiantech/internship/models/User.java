package vn.asiantech.internship.models;

public class User {
    private int imgAvatar;
    private String email;
    private String uri;

    public User(int imgAvatar, String email, String uri) {
        this.imgAvatar = imgAvatar;
        this.email = email;
        this.uri = uri;
    }

    public int getImgAvatar() {
        return imgAvatar;
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
