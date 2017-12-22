package vn.asiantech.internship.models;

import android.content.Intent;

public class Option {
    public int icon;
    public String name;
    private boolean isSelected;
    private Intent intent;

    public Option(int icon, String name, boolean isSelected, Intent intent) {
        this.icon = icon;
        this.name = name;
        this.isSelected = isSelected;
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected() {
        isSelected = !isSelected;
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
