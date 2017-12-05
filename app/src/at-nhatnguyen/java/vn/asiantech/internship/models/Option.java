package vn.asiantech.internship.models;

public class Option {
    private String optionName;
    private int icon;

    public Option(String option, int icon) {
        this.optionName = option;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getOptionName() {
        return optionName;
    }
}
