package vn.asiantech.internship.model;

/**
 * Created by anh.quach on 11/28/17.
 */

public class NewFeed {
    private String name;
    private String status;
    private int sumReact=0;

    public NewFeed(String name, String status){
        this.name = name;
        this.status = status;
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

    public int getSumReact() {
        return sumReact;
    }

    public void setSumReact(int sumReact) {
        this.sumReact = sumReact;
    }
}
