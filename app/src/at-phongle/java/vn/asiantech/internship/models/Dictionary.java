package vn.asiantech.internship.models;

/**
 * Created by phongle on 14/12/2560.
 * Model dictionary
 */

public class Dictionary {
    private String vietnamText;
    private String englishText;
    private int image;

    public Dictionary(String vietnam, String english, int image) {
        this.vietnamText = vietnam;
        this.englishText = english;
        this.image = image;
    }

    public String getVietnamText() {
        return vietnamText;
    }

    public void setVietnamText(String vietnamText) {
        this.vietnamText = vietnamText;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
