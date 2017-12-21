package vn.asiantech.internship.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anh.quach on 12/14/17.
 * Class model Card
 */
public class Card implements Parcelable {
    private String englishWord;
    private String vietnamWord;
    private int image;

    public Card(String englishWord, String vietnamWord, int image) {
        this.englishWord = englishWord;
        this.vietnamWord = vietnamWord;
        this.image = image;
    }

    private Card(Parcel in) {
        englishWord = in.readString();
        vietnamWord = in.readString();
        image = in.readInt();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(englishWord);
        dest.writeString(vietnamWord);
        dest.writeInt(image);
    }
}
