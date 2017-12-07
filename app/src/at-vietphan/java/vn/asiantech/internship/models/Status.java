package vn.asiantech.internship.models;

/**
 * class status
 */
public class Status {
    private String name;
    private String content;
    private int countLike;

    public Status(String name, String content, int countLike) {
        this.name = name;
        this.content = content;
        this.countLike = countLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }
}
