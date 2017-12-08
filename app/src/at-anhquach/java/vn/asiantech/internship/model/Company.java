package vn.asiantech.internship.model;

public class Company {
    private int id;
    private String name;
    private String slogan;

    public Company(int id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }

    public Company() {

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
