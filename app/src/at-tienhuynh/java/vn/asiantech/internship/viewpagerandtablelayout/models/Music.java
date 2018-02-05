package vn.asiantech.internship.viewpagerandtablelayout.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TienHuynh on 17/01/2018.
 * AsianTech Co., Ltd
 */
public class Music implements Parcelable {
    private String name;
    private String singer;
    private int song;
    private String time;

    public Music(String name, String singer, int song, String time) {
        this.name = name;
        this.singer = singer;
        this.song = song;
        this.time = time;
    }

    public Music(Parcel in) {
        name = in.readString();
        singer = in.readString();
        song = in.readInt();
        time = in.readString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(singer);
        parcel.writeInt(song);
        parcel.writeString(time);
    }
}
