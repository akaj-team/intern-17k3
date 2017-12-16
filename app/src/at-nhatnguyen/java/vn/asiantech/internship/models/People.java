package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 06/12/2017.
 * Create people models
 */
public class People {
    private int iDPeople;
    private String namePeople;
    private int agePeople;

    public People(){
        // No-op
    }

    public int getiDPeople() {
        return iDPeople;
    }

    public void setiDPeople(int iDPeople) {
        this.iDPeople = iDPeople;
    }

    public String getNamePeople() {
        return namePeople;
    }

    public void setNamePeople(String namePeople) {
        this.namePeople = namePeople;
    }

    public int getAgePeople() {
        return agePeople;
    }

    public void setAgePeople(int agePeople) {
        this.agePeople = agePeople;
    }
}
