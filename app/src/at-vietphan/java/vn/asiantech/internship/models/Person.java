package vn.asiantech.internship.models;

/**
 * Class Person
 */
public class Person {
    private int image;
    private String email;
    private String uri;

    public Person(int image, String email, String uri) {
        this.image = image;
        this.email = email;
        this.uri = uri;
    }

    public int getImage() {
        return image;
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
