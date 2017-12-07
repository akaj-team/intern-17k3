package vn.asiantech.internship.savedata.models;

/**
 * Created at 2017
 * Created by jackty on 07/12/2017.
 */

public class Employee {
    private int id;
    private int id_user;
    private int id_company;

    public Employee(int id_user, int id_company) {
        this.id_user = id_user;
        this.id_company = id_company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }
}
