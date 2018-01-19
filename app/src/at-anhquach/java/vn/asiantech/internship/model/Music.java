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
    private String singer;
    private int avatar;
    private int music;

    public Music(String id, String tittle, String singer, int avatar, int music) {
        this.id = id;
        this.tittle = tittle;
        this.singer = singer;
        this.avatar = avatar;
        this.music = music;
    }

    protected Music(Parcel in) {
        id = in.readString();
        tittle = in.readString();
        singer = in.readString();
        avatar = in.readInt();
        music = in.readInt();
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tittle);
        dest.writeString(singer);
        dest.writeInt(avatar);
        dest.writeInt(music);
    }

    public static Creator<Music> getCREATOR() {
        return CREATOR;
    }
}
