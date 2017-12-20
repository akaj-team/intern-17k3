package vn.asiantech.internship.models;

/**
 * Create item view : Person
 */
public class Person {
    private String name;
    private int countLike;
    private String status;

    public Person(String name, int countLike, String status) {
        this.name = name;
        this.status = status;
        this.countLike = countLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public String getStatus() {
        return status;
    }
}
