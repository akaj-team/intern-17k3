package vn.asiantech.internship.models;

public class Company {
    private int id;
    private String name;
    private String slogan;

    public Company() {
    }

    public Company(String name, String slogan) {
        this.name = name;
        this.slogan = slogan;
    }

    public Company(int id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
