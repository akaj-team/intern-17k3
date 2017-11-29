package vn.asiantech.internship.models;

/**
 * Created by huong.nguyen on 11/28/17.
 */

public class Person {

    private String name;
    private String description;
    private String count;

    public Person(String name, String description, String count) {
        this.name = name;
        this.description = description;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
