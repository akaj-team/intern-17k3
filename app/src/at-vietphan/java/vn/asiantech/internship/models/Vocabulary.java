package vn.asiantech.internship.models;

/**
 * Created by vietphan on 14/12/2017.
 * Class Vocabulary
 */
public class Vocabulary {
    private String FirstLanguage;
    private int imageAnimal;
    private String SecondLanguage;

    public Vocabulary(String firstLanguage, int imageAnimal, String secondLanguage) {
        FirstLanguage = firstLanguage;
        this.imageAnimal = imageAnimal;
        SecondLanguage = secondLanguage;
    }

    public String getFirstLanguage() {
        return FirstLanguage;
    }

    public void setFirstLanguage(String firstLanguage) {
        FirstLanguage = firstLanguage;
    }

    public int getImageAnimal() {
        return imageAnimal;
    }

    public void setImageAnimal(int imageAnimal) {
        this.imageAnimal = imageAnimal;
    }

    public String getSecondLanguage() {
        return SecondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        SecondLanguage = secondLanguage;
    }
}
