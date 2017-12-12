package vn.asiantech.internship.loadImage;

import android.widget.ImageView;

public class ItemImage {
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ItemImage(ImageView image) {
        this.image = image;
    }
}
