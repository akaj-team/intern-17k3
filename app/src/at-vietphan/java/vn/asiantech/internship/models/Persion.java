package vn.asiantech.internship.models;

/**
 * Class user
 */
public class Persion {
    private int img;
    private String email;
    private String uri;

    public Persion(int img, String email, String uri) {
        this.img = img;
        this.email = email;
        this.uri = uri;
    }

    public int getImg() {
        return img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
