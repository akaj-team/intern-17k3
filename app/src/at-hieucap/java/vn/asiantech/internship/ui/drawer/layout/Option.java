package vn.asiantech.internship.ui.drawer.layout;

public class Option {
    public int icon;
    public String name;
    private boolean Clicked;

    Option(int icon, String name, boolean isClicked) {
        this.icon = icon;
        this.name = name;
        this.Clicked = isClicked;
    }

    boolean isClicked() {
        return Clicked;
    }

    void setClicked() {
        Clicked = !Clicked;
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
