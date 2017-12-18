package vn.asiantech.internship.models;

import android.content.Intent;

/**
 * This class used for option object
 */
public class Option {
    private String name;
    private int icon;
    private Intent intent;

    public Option(String name, int icon, Intent intent) {
        this.name = name;
        this.icon = icon;
        this.intent = intent;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
