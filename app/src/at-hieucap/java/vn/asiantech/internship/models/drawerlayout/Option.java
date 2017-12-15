package vn.asiantech.internship.models.drawerlayout;

public class Option {
    public int icon;
    public String name;
    private boolean Clicked;

    public Option(int icon, String name, boolean isClicked) {
        this.icon = icon;
        this.name = name;
        this.Clicked = isClicked;
    }

    public boolean isClicked() {
        return Clicked;
    }

    public void setClicked() {
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
