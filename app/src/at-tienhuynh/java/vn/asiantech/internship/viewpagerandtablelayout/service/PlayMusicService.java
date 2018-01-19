package vn.asiantech.internship.viewpagerandtablelayout.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import vn.asiantech.internship.viewpagerandtablelayout.models.Music;

/**
 * Created by TienHuynh on 17/01/2018.
 * AsianTech Co., Ltd
 */
public class PlayMusicService extends Service implements MediaPlayer.OnCompletionListener {

    private static MediaPlayer mMediaPlayer;
    private ArrayList<Music> mMusicLists = new ArrayList<>();
    private int mPosition;
    private CountDownTimer mCountTimeStartSong;
    private CountDownTimer mCountTimeResumeSong;
    private long mTimeResuming;
    final Intent sendTimeIntent = new Intent();

    @Override
    public void onCreate() {
        // init Media
        initMedia();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case "data":
                    mMusicLists = intent.getParcelableArrayListExtra("list");
                    break;
                case "play":
                    resumeMusic();
                    break;
                case "pause":
                    pauseMusic();
                    break;
                case "potion":
                    mPosition = intent.getIntExtra("i", 0);
                    playSongMusic(mPosition);
                    break;
                case "next":
                    mPosition += 1;
                    if (mPosition < mMusicLists.size()) {
                        playSongMusic(mPosition);
                    } else {
                        mPosition = mMusicLists.size() - 1;
                    }
                    break;
                case "previous":
                    mPosition -= 1;
                    if (mPosition >= 0) {
                        playSongMusic(mPosition);
                    } else {
                        mPosition = 0;
                    }
                    break;
                case "stop":
                    mMediaPlayer.stop();
                    break;
                case "move":
                    int timeMoving = intent.getIntExtra("timeMoving", 0);
                    mMediaPlayer.seekTo(timeMoving);
                    break;
            }
        }
        return START_STICKY;
    }

    /**
     * pause music when click pause button
     */
    private void pauseMusic() {
        mMediaPlayer.pause();
        mCountTimeStartSong.cancel();
        if (mCountTimeResumeSong != null) {
            mCountTimeResumeSong.cancel();
        }
    }

    /**
     * resume music when click play button
     */

    private void resumeMusic() {
        mMediaPlayer.start();
        sendTimeIntent.setAction("sendTime");
        mCountTimeResumeSong = new CountDownTimer(mTimeResuming, 1000) {
            @Override
            public void onTick(long l) {
                sendTimeIntent.putExtra("currentTime", mMediaPlayer.getCurrentPosition());
                sendTimeIntent.putExtra("totalTime", mMediaPlayer.getDuration());
                sendBroadcast(sendTimeIntent);
                mTimeResuming = l;
            }

            @Override
            public void onFinish() {
                sendTimeIntent.putExtra("currentTime", mMediaPlayer.getCurrentPosition());
                sendTimeIntent.putExtra("totalTime", mMediaPlayer.getDuration());
                sendBroadcast(sendTimeIntent);
            }
        }.start();
    }

    /**
     * Init Media Player
     */
    private void initMedia() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.reset();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Play the song
     */
    private void playSongMusic(int position) {
        try {
            Uri myUri = Uri.parse("android.resource://" + this.getPackageName() + "/" + mMusicLists.get(position).getSong());
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(this, myUri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            sendTimeIntent.setAction("sendTime");
            mCountTimeStartSong = new CountDownTimer(mMediaPlayer.getDuration(), 1000) {
                @Override
                public void onTick(long l) {
                    sendTimeIntent.putExtra("currentTime", mMediaPlayer.getCurrentPosition());
                    sendTimeIntent.putExtra("totalTime", mMediaPlayer.getDuration());
                    sendBroadcast(sendTimeIntent);
                    mTimeResuming = l;
                }

                @Override
                public void onFinish() {
                    sendTimeIntent.putExtra("currentTime", mMediaPlayer.getCurrentPosition());
                    sendTimeIntent.putExtra("totalTime", mMediaPlayer.getDuration());
                    sendBroadcast(sendTimeIntent);
                }
            };
            mCountTimeStartSong.start();
        } catch (IOException e) {
            Log.d("XXX", e.getMessage());
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //no -opp
    }
}
