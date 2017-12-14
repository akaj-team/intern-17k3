package vn.asiantech.internship.viewpagerandtablelayout.models;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public class SlideHomeFragment {
    private String englishText;
    private String vietnamText;
    private int image;

    public SlideHomeFragment(String englishText, String vietnamText, int image) {
        this.englishText = englishText;
        this.vietnamText = vietnamText;
        this.image = image;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getVietnamText() {
        return vietnamText;
    }

    public void setVietnamText(String vietnamText) {
        this.vietnamText = vietnamText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
