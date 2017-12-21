package vn.asiantech.internship.ui.savedata.ex3.model;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class Employee {
    private int id;
    private int idUser;
    private int idCompany;

    public Employee() {
    }

    public Employee(int id, int id_user, int id_company) {
        this.id = id;
        this.idUser = id_user;
        this.idCompany = id_company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIDUser() {
        return idUser;
    }

    public void setIDUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIDCompany() {
        return idCompany;
    }

    public void setIDCompany(int idCompany) {
        this.idCompany = idCompany;
    }
}
