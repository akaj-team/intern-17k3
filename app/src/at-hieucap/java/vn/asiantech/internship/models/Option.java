package vn.asiantech.internship.models;

import android.content.Intent;

public class Option {
    public int icon;
    public String name;
    private boolean isClick;
    private Intent intent;

    public Option(int icon, String name, boolean isClick, Intent intent) {
        this.icon = icon;
        this.name = name;
        this.isClick = isClick;
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClicked() {
        isClick = !isClick;
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
