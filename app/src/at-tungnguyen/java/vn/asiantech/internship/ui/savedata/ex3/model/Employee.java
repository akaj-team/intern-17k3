package vn.asiantech.internship.ui.savedata.ex3.model;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */

public class Employee {
    private int id;
    private int id_user;
    private int id_company;

    public Employee() {
    }

    public Employee(int id, int id_user, int id_company) {
        this.id = id;
        this.id_user = id_user;
        this.id_company = id_company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIDUser() {
        return id_user;
    }

    public void setIDUser(int id_user) {
        this.id_user = id_user;
    }

    public int getIDCompany() {
        return id_company;
    }

    public void setIDCompany(int id_company) {
        this.id_company = id_company;
    }
}
