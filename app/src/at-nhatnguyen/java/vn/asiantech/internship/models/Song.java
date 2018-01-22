package vn.asiantech.internship.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hoangnhat on 17/01/2018.
 * Class model song
 */

public class Song implements Parcelable {
    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    private String name;
    private int avatar;
    private int resource;

    public Song(String name, int avatar, int resource) {
        this.name = name;
        this.avatar = avatar;
        this.resource = resource;
    }

    protected Song(Parcel in) {
        name = in.readString();
        avatar = in.readInt();
        resource = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(avatar);
        dest.writeInt(resource);
    }
}
