package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 17/01/2018.
 * Class model song
 */

public class Song {
    private String name;
    private int avatar;
    private int resource;

    public Song(String name, int avatar, int resource) {
        this.name = name;
        this.avatar = avatar;
        this.resource = resource;
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
}
