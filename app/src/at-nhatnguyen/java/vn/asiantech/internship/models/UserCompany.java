package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 06/12/2017.
 */

public class UserCompany {
    private int iDUser;
    private String nameUser;
    private int ageUser;

    public UserCompany(int idUser, String nameUser, int ageUser) {
        this.iDUser = idUser;
        this.nameUser = nameUser;
        this.ageUser = ageUser;
    }

    public int getIDUser() {
        return iDUser;
    }

    public void setIDUser(int idUser) {
        this.iDUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getAgeUser() {
        return ageUser;
    }

    public void setAgeUser(int ageUser) {
        this.ageUser = ageUser;
    }
}
