package vn.asiantech.internship.models;

/**
 * Created by phongle on 12/12/2560.
 * model Image
 */
public class Image {
    private String imageUrl;
    private int imageDrawerble;
    private String information;

    public Image(String imageUrl, int imageDrawerble, String information) {
        this.imageUrl = imageUrl;
        this.imageDrawerble = imageDrawerble;
        this.information = information;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageDrawerble() {
        return imageDrawerble;
    }

    public void setImageDrawerble(int imageDrawerble) {
        this.imageDrawerble = imageDrawerble;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
