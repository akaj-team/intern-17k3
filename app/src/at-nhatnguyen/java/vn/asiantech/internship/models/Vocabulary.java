package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 14/12/2017.
 * Class use for viewpager of homeFragment
 */
public class Vocabulary {
    private String english;
    private int image;
    private String vietNam;

    public Vocabulary(String english, int image, String vietNam) {
        this.english = english;
        this.image = image;
        this.vietNam = vietNam;
    }

    public String getEnglish() {
        return english;
    }

    public int getImage() {
        return image;
    }

    public String getVietNam() {
        return vietNam;
    }
}
