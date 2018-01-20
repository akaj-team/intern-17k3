package vn.asiantech.internship.ui.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;


/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class MusicPlayService extends Service {
    static MediaPlayer mMediaPlayer;
    int[] mList;
    Music music;
    private int mPositon = 0;
    private final IBinder musicBind = new MusicBinder();

    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    //binder
    public class MusicBinder extends Binder {
        MusicPlayService getService() {
            return MusicPlayService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mList = new int[]{R.raw.music_2, R.raw.music3, R.raw.music3, R.raw.music3};
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
//            if (intent.getAction() != null) {
//                switch (intent.getAction()) {
//                    case "data":
//                        int mPosition = intent.getIntExtra("position", 0);
//                        Log.d("AAAA", "onStartCommand: " + intent.getIntExtra("position", 0));
//                        Music music = intent.getParcelableExtra("music");
//                        mMediaPlayer = MediaPlayer.create(getApplicationContext(), mList[mPositon]);
//                        Log.d("AAAA", "onStartCommand: " + music.getNameMusic());
//                    case "music":
//                }
//            }
        }
        return START_STICKY;
    }

    public void onNext() {
        mPositon++;
        if (mPositon < mList.length) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            } else {
                mPositon = mList.length;
            }
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), mList[mPositon]);
            mMediaPlayer.start();
        }
    }

    public void onPrevious() {
        mPositon--;
        if (mPositon < mList.length) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            } else {
                mPositon = mList.length;
            }
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), mList[mPositon]);
            mMediaPlayer.start();
        }
    }

    public void onPause() {
        mMediaPlayer.pause();
    }

    public boolean isPlaying() {
        mMediaPlayer.isPlaying();
        return false;
    }

    public void onPlay() {
        mMediaPlayer.start();
    }

    public void onStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    public String getNameMusic(Music music){
        return music.getNameMusic();
    }
    public void onItemMusicClick(int position) {
        onStop();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), mList[position]);
        mMediaPlayer.start();
    }
}
