package vn.asiantech.internship.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anh.quach on 1/17/18.
 * Music model.
 */
public class Music implements Parcelable {
    private String id;
    private String tittle;
    private String artist;
    private int avatar;
    private int audio;

    public Music(String id, String tittle, String singer, int avatar, int music) {
        this.id = id;
        this.tittle = tittle;
        this.artist = singer;
        this.avatar = avatar;
        this.audio = music;
    }

    protected Music(Parcel in) {
        id = in.readString();
        tittle = in.readString();
        artist = in.readString();
        avatar = in.readInt();
        audio = in.readInt();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tittle);
        dest.writeString(artist);
        dest.writeInt(avatar);
        dest.writeInt(audio);
    }

    public static Creator<Music> getCREATOR() {
        return CREATOR;
    }
}
