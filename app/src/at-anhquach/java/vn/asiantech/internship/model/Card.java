package vn.asiantech.internship.model;

/**
 * Created by anh.quach on 12/14/17.
 * Class model Card
 */
public class Card {
    private int idCard;
    private String englishWord;
    private String vietnamWord;
    private int image;

    public Card(int idCard, String englishWord, String vietnamWord, int image) {
        this.idCard = idCard;
        this.englishWord = englishWord;
        this.vietnamWord = vietnamWord;
        this.image = image;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getVietnamWord() {
        return vietnamWord;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
}
