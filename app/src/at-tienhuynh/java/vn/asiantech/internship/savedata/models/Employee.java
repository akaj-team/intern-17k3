package vn.asiantech.internship.savedata.models;

/**
 * Created at 2017
 * Created by jackty on 07/12/2017.
 */

public class Employee {
    private int id;
    private int idUser;
    private int idCompany;

    public Employee(int idUser, int idCompany) {
        this.idUser = idUser;
        this.idCompany = idCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }
}
