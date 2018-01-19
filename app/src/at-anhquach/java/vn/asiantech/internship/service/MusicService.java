package vn.asiantech.internship.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;

import vn.asiantech.internship.model.Music;
import vn.asiantech.internship.notifcation.PlayMusicNotification;

/**
 * Created by anh.quach on 1/18/18.
 * Service Music.
 */
public class MusicService extends Service {
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private ArrayList<Music> mMusicList = new ArrayList<>();
    private int mPos;
    private Intent mIntent = new Intent();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case "data":
                        mMusicList = intent.getParcelableArrayListExtra("listData");
                        break;
                    case "start":
                        mPos = intent.getIntExtra("position", 0);
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                        PlayMusicNotification.notify(this, 1, mMusicList.get(mPos));
                        break;
                    case "play":
                        mMediaPlayer.start();
                        break;
                    case "pause":
                        mMediaPlayer.pause();
                        break;
                    case "next":
                        mPos = (mPos + 1) % mMusicList.size();
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                        break;
                    case "previous":
                        if (mPos > 0) {
                            mPos = mPos - 1;
                        } else {
                            mPos = mMusicList.size() - 1;
                        }
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                }
            }
        }
        return START_STICKY;
    }

    private void setMedia(int position) {
        mMediaPlayer.reset();
        Uri mMediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + mMusicList.get(position).getMusic());
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(this, mMediaPath);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDataBroadcast(int position) {
        mIntent.setAction("sendData");
        mIntent.putExtra("music", mMusicList.get(position));
        sendBroadcast(mIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
