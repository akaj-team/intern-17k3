package vn.asiantech.internship.ui.drawer.layout;

public class Option {
    public int icon;
    public String name;
    private boolean isClicked;

    Option(int icon, String name, boolean isClicked) {
        this.icon = icon;
        this.name = name;
        this.isClicked = isClicked;
    }

    boolean isClicked() {
        return isClicked;
    }

    void setClicked() {
        isClicked = !isClicked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
