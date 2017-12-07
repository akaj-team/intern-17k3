package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 06/12/2017.
 * create Company models
 */

public class Company {
    private int idCompany;
    private String nameCompany;
    private String sloganCompany;

    public Company() {
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getSloganCompany() {
        return sloganCompany;
    }

    public void setSloganCompany(String sloganCompany) {
        this.sloganCompany = sloganCompany;
    }
}
