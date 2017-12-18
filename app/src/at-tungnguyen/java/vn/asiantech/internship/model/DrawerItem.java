package vn.asiantech.internship.model;

import android.content.Intent;

public class DrawerItem {
    private String name;
    private int type;
    private int imageResource;
    private String imageUri;
    private Intent intent;

    public DrawerItem(String name, int type, int imageResource, String imageUri, Intent intent) {
        this.name = name;
        this.type = type;
        this.imageResource = imageResource;
        this.imageUri = imageUri;
        this.intent = intent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
