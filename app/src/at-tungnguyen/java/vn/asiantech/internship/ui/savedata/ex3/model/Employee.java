package vn.asiantech.internship.ui.savedata.ex3.model;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */

public class Employee {
    private int id;
    private int id_user;
    private int id_employee;

    public Employee() {
    }

    public Employee(int id, int id_user, int id_employee) {
        this.id = id;
        this.id_user = id_user;
        this.id_employee = id_employee;
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

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }
}
