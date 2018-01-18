package vn.asiantech.internship.ui.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;


/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class MusicPlayService extends Service {
    MediaPlayer play;
    List<MediaPlayer> mList;
    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mList.add(MediaPlayer.create(getApplicationContext(), R.raw.music));
        mList.add(MediaPlayer.create(getApplicationContext(), R.raw.music_2));
        Toast.makeText(getApplicationContext(), "Đang nghe nhạc", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play.start();
        if (intent != null) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case "data":
                        if (intent.getExtras() != null) {
                            Bundle bundle = intent.getExtras().getBundle("data");
                                Music music = bundle.getParcelable("music");
                        }
                }
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        play.release();
        Toast.makeText(getApplicationContext(), "Dừng nhạc", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
