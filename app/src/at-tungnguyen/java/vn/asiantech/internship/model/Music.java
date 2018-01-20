package vn.asiantech.internship.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class Music implements Parcelable{
    private String nameMusic;
    private String single;
    private boolean isPlay;

    public Music() {
    }

    public Music(String nameMusic, String single, boolean isPlay) {
        this.nameMusic = nameMusic;
        this.single = single;
        this.isPlay = isPlay;
    }

    private Music(Parcel in) {
        nameMusic = in.readString();
        single = in.readString();
        isPlay = in.readByte() != 0;
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

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameMusic);
        parcel.writeString(single);
        parcel.writeByte((byte) (isPlay ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Music{" +
                "nameMusic='" + nameMusic + '\'' +
                ", single='" + single + '\'' +
                ", isPlay=" + isPlay +
                '}';
    }
}
