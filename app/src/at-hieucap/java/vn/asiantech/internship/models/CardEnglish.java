package vn.asiantech.internship.models;

public class CardEnglish {
    private String nameEnglish;
    private int image;
    private String nameVietNam;

    public CardEnglish(String nameEnglish, int image, String nameVietNam) {

        this.nameEnglish = nameEnglish;
        this.image = image;
        this.nameVietNam = nameVietNam;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameVietNam() {
        return nameVietNam;
    }

    public void setNameVietNam(String nameVietNam) {
        this.nameVietNam = nameVietNam;
    }
}
