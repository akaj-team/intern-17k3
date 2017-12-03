package vn.asiantech.internship.models;

public class Option {
    private String option;
    private int icon;

    public Option(String option, int icon) {
        this.option = option;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getOption() {
        return option;
    }

}
