package vn.asiantech.internship.models;

/**
 * class Issue
 */
public class Issue {
    private int icon;
    private String name;

    public Issue(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
