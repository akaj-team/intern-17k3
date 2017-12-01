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
    private int Type;

    public DrawerMenu(String emailHeader, int imgMenu, int type) {
        EmailHeader = emailHeader;
        ImgMenu = imgMenu;
        Type = type;
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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
