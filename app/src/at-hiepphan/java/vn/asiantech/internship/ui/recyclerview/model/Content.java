package vn.asiantech.internship.ui.recyclerview.model;

public class Content {
    private String name;
    private String status;
    private int likeCount;

    public Content(String name, String status, int likeCount) {
        this.name = name;
        this.status = status;
        this.likeCount = likeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
