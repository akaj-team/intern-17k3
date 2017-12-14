package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 14/12/2017.
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

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getVietNam() {
        return vietNam;
    }

    public void setVietNam(String vietNam) {
        this.vietNam = vietNam;
    }
}
