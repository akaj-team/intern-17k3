package vn.asiantech.internship.recyclerview.models;

public class Posts {
    private String subject;
    private String description;
    private int likeNumber;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Posts(String subject, String description, int likeNumber) {
        this.subject = subject;
        this.description = description;
        this.likeNumber = likeNumber;
    }
}
