package vn.asiantech.internship.drawer;

public class DrawerItem {
    private String name;
    private int type;
    private int imageResource;
    private String imageUri;

    public DrawerItem(String name, int type, int imageResource, String imageUri) {
        this.name = name;
        this.type = type;
        this.imageResource = imageResource;
        this.imageUri = imageUri;
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
        return imageResource;
    }

    public void setImageresource(int imageresource) {
        this.imageResource = imageresource;
    }

    public String getImageuri() {
        return imageUri;
    }

    public void setImageuri(String imageuri) {
        this.imageUri = imageuri;
    }
}
