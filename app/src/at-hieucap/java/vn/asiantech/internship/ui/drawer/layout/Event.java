package vn.asiantech.internship.ui.drawer.layout;

import android.widget.ImageView;

public class Event {
    public ImageView icon;
    public String name;

    public Event(ImageView icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
}
