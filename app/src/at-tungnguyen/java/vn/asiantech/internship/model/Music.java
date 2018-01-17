package vn.asiantech.internship.model;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class Music {
    private String nameMusic;
    private String single;
    private boolean isPlay;

    public Music(String nameMusic, String single,boolean isPlay) {
        this.nameMusic = nameMusic;
        this.single = single;
        this.isPlay = isPlay;
    }

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
}
