package vn.asiantech.internship.models;

import android.content.Intent;

/**
 * class Issue
 */
public class Issue {
    private int icon;
    private String name;
    private Intent intent;

    public Issue(int icon, String name, Intent intent) {
        this.icon = icon;
        this.name = name;
        this.intent = intent;
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

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
