package vn.asiantech.internship.ui.viewpager.model;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 14/12/2017.
 */

public class Animal {
    private String nameEngLish;
    private int imgAnimal;
    private String nameVietNamese;

    public Animal(String nameEngLish, int imgAnimal, String nameVietNamese) {
        this.nameEngLish = nameEngLish;
        this.imgAnimal = imgAnimal;
        this.nameVietNamese = nameVietNamese;
    }

    public String getNameEngLish() {
        return nameEngLish;
    }

    public void setNameEngLish(String nameEngLish) {
        this.nameEngLish = nameEngLish;
    }

    public int getImgAnimal() {
        return imgAnimal;
    }

    public void setImgAnimal(int imgAnimal) {
        this.imgAnimal = imgAnimal;
    }


    public String getNameVietNamese() {
        return nameVietNamese;
    }

    public void setNameVietNamese(String nameVietNamese) {
        this.nameVietNamese = nameVietNamese;
    }
}
