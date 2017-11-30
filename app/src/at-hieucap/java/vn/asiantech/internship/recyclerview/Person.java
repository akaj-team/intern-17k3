package vn.asiantech.internship.recyclerview;

public class Person {
    private String name;
    private boolean isLike;
    private boolean isUnlike;
    private String value;
    private String status;

    Person(String name, boolean isLike, boolean isUnlike, String value, String status) {
        this.name = name;
        this.isLike = isLike;
        this.isUnlike = isUnlike;
        this.value = value;
        this.status = status;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isLike() {

        return isLike;
    }

    public void setLike(boolean like) {

        isLike = like;
    }

    public boolean isUnlike() {

        return isUnlike;
    }

    public void setUnlike(boolean unlike) {

        isUnlike = unlike;
    }

    String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;

    }

    String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
