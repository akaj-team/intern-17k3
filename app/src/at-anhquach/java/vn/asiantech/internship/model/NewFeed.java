package vn.asiantech.internship.model;

public class NewFeed {
    private String name;
    private String status;
    private int sumReact;

    public NewFeed(String name, String status, int sumReact) {
        this.name = name;
        this.status = status;
        this.sumReact = sumReact;
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

    public int getSumReact() {
        return sumReact;
    }

    public void setSumReact(int sumReact) {
        this.sumReact = sumReact;
    }
}
