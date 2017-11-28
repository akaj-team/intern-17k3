package vn.asiantech.internship.model;

/**
 * Created by anh.quach on 11/28/17.
 */

public class NewFeed {
    private String name;
    private String status;

    public NewFeed(String name, String status){
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }
}
