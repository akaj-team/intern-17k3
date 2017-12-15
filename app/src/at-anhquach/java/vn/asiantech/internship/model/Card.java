package vn.asiantech.internship.model;

/**
 * Created by anh.quach on 12/14/17.
 */

public class Card {
    String englishWord;
    String vietnamWord;
    int image;

    public Card(String englishWord, String vietnamWord, int image) {
        this.englishWord = englishWord;
        this.vietnamWord = vietnamWord;
        this.image = image;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getVietnamWord() {
        return vietnamWord;
    }

    public void setVietnamWord(String vietnamWord) {
        this.vietnamWord = vietnamWord;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
