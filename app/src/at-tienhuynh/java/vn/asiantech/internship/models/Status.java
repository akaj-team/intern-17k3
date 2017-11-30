package vn.asiantech.internship.models;

/**
 * Created at 2017
 * Created by jackty on 29/11/2017.
 */

public class Status {
    private String title;
    private String description;
    private int numLike;

    public Status(String title, String description, int numlike) {
        this.title = title;
        this.description = description;
        this.numLike = numlike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumlike() {
        return numLike;
    }

    public void setNumlike(int numlike) {
        this.numLike = numlike;
    }
}
