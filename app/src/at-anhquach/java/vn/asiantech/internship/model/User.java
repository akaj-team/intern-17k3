package vn.asiantech.internship.model;

/**
 * Define class User
 */
public class User {
    private int id;
    private String name;
    private int age;
    private int usename;
    private int password;

    public User() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUsename() {
        return usename;
    }

    public void setUsename(int usename) {
        this.usename = usename;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
