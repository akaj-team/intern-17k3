package vn.asiantech.internship.models;

public class Comment {
    private String name;
    private String commentContent;
    private int totalLike;

    public Comment(String name, String commentContent, int totalLike) {
        this.name = name;
        this.commentContent = commentContent;
        this.totalLike = totalLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentContent() {
        return commentContent;
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
