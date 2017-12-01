package vn.asiantech.internship.models;

public class Comment {
    private String name;
    private String content;
    private int totalLike;

    public Comment(String name, String commentContent, int totalLike) {
        this.name = name;
        this.content = commentContent;
        this.totalLike = totalLike;
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

    public int getTotalLike() {
        return totalLike;
    }

    public void clickLike() {
        totalLike++;
    }

    public void clickDislike() {
        totalLike--;
    }
}