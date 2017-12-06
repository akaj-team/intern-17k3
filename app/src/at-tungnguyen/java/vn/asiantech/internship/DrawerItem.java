package vn.asiantech.internship;

public class DrawerItem {
    private String name;
    private int type;
    private int imageresource;
    private String imageuri;

    public DrawerItem(String name, int type, int imageresource, String imageuri) {
        this.name = name;
        this.type = type;
        this.imageresource = imageresource;
        this.imageuri = imageuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageresource() {
        return imageresource;
    }

    public void setImageresource(int imageresource) {
        this.imageresource = imageresource;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }
}
