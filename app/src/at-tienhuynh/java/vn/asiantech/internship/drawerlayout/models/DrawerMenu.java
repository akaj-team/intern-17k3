package vn.asiantech.internship.drawerlayout.models;

/**
 * Created at 2017
 * Created by jackty on 01/12/2017.
 */
public class DrawerMenu {

    private int numberImage;
    private String nameMenu;

    public DrawerMenu(int numberImage, String nameMenu) {
        this.numberImage = numberImage;
        this.nameMenu = nameMenu;
    }

    public int getNumberImage() {
        return numberImage;
    }

    public void setNumberImage(int numberImage) {
        this.numberImage = numberImage;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }
}
