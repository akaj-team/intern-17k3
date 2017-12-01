package vn.asiantech.internship.drawerlayout.models;

/**
 * Created at 2017
 * Created by jackty on 01/12/2017.
 */

public class DrawerMenu {

    public static final int HEADER_TYPE = 0;
    public static final int ITEM_TYPE = 1;
    private String EmailHeader;
    private int ImgMenu;
    private String NameMenuItem;
    private int Type;

    public DrawerMenu(String emailHeader, int imgMenu, String nameMenuItem, int type) {
        EmailHeader = emailHeader;
        ImgMenu = imgMenu;
        NameMenuItem = nameMenuItem;
        Type = type;
    }

    public static int getHeaderType() {
        return HEADER_TYPE;
    }

    public static int getItemType() {
        return ITEM_TYPE;
    }

    public String getEmailHeader() {
        return EmailHeader;
    }

    public void setEmailHeader(String emailHeader) {
        EmailHeader = emailHeader;
    }

    public int getImgMenu() {
        return ImgMenu;
    }

    public void setImgMenu(int imgMenu) {
        ImgMenu = imgMenu;
    }

    public String getNameMenuItem() {
        return NameMenuItem;
    }

    public void setNameMenuItem(String nameMenuItem) {
        NameMenuItem = nameMenuItem;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
