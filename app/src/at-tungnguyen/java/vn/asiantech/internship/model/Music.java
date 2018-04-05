package vn.asiantech.internship.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class Music implements Parcelable {
    private String nameMusic;
    private String single;
    private int uriMusic;

    public Music() {
    }

    public Music(String nameMusic, String single, int uriMusic) {
        this.nameMusic = nameMusic;
        this.single = single;
        this.uriMusic = uriMusic;

    }

    private Music(Parcel in) {
        nameMusic = in.readString();
        single = in.readString();
        uriMusic = in.readInt();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public String getNameMusic() {
        return nameMusic;
    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic = nameMusic;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public int getUriMusic() {
        return uriMusic;
    }

    public void setUriMusic(int uriMusic) {
        this.uriMusic = uriMusic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameMusic);
        parcel.writeString(single);
        parcel.writeInt(uriMusic);
    }
}
