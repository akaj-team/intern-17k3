package vn.asiantech.internship.models;

/**
 * Created by tiboo on 20/12/2017.
 * Create item list : FootballTeam
 */

public class FootballTeam {
    private int logo;
    private String nameTeam;
    private String nation;

    public FootballTeam(int logo, String nameTeam, String nation) {
        this.logo = logo;
        this.nameTeam = nameTeam;
        this.nation = nation;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
