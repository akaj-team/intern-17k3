package vn.asiantech.internship.ui.recyclerview.model;


public class DataContent {
    private String name;
    private String status;
    private int likeCount;

    public DataContent(String name, String status) {
        this.name = name;
        this.status = status;
        likeCount = 0;
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
