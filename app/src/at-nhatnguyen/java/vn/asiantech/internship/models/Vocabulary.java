package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 14/12/2017.
 * Class use for viewpager of homeFragment
 */
public class Vocabulary {
    private String englishLanguage;
    private int image;
    private String vietnamese;

    public Vocabulary(String englishLanguage, int image, String vietnamese) {
        this.englishLanguage = englishLanguage;
        this.image = image;
        this.vietnamese = vietnamese;
    }

    public String getEnglishLanguage() {
        return englishLanguage;
    }

    public int getImage() {
        return image;
    }

    public String getVietnamese() {
        return vietnamese;
    }
}
