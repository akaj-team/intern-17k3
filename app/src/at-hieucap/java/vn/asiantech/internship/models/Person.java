package vn.asiantech.internship.models;

public class Person {
    private String name;
    private String value;
    private String status;
    private int countLine;

    Person(String name, String value, String status) {
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    String getValue() {

        return value;
    }

    String getStatus() {

        return status;
    }

}
