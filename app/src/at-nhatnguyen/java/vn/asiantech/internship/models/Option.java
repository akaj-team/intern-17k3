package vn.asiantech.internship.models;

/**
 * This class used for option object
 */
public class Option {
    private String name;
    private int icon;

    public Option(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
