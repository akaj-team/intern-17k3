package vn.asiantech.internship.ui.viewpager.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by hoangnhat on 17/01/2018.
 * Class service
 */

public class ServiceMusic extends Service {
    private static final String TIME_CURRENT = "CurrentTime";
    private static final String TIME_TOTAL = "TotalTime";
    int a;
    private MediaPlayer mediaPlayer;
    private CountDownTimer countDownTimerStart;
    private CountDownTimer countDownTimerResume;
    private Intent sendTime;
    private long timeRemaining;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        sendTime = new Intent();
        timeRemaining = 0;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case "Play":
                        int position = intent.getIntExtra("position", 0);
                        a = position;
                        playMusic(position);
                        break;
                    case "Resume":
                        resumeMusic();
                        break;
                    case "Move":
                        int a = intent.getIntExtra("Move", 0);
                        mediaPlayer.seekTo(a);
                        break;
                    case "Stop":
                        stopMusic();
                        break;
                    case "Pause":
                        pauseMusic();
                }
            }
        }
        return START_NOT_STICKY;
    }

    private void stopMusic() {
        mediaPlayer.stop();
        if (countDownTimerStart != null) {
            countDownTimerStart.cancel();
        }
        if (countDownTimerResume != null) {
            countDownTimerResume.cancel();
        }
    }

    private void resumeMusic() {
        mediaPlayer.start();
        sendTime.setAction("CheckPlay");
        countDownTimerResume = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sendTime.putExtra(TIME_CURRENT, mediaPlayer.getCurrentPosition());
                sendTime.putExtra(TIME_TOTAL, mediaPlayer.getDuration());
                sendBroadcast(sendTime);
                timeRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                sendTime.putExtra(TIME_CURRENT, mediaPlayer.getCurrentPosition());
                sendTime.putExtra(TIME_TOTAL, mediaPlayer.getDuration());
                sendBroadcast(sendTime);
            }
        };
        countDownTimerResume.start();
    }

    private void pauseMusic() {
        mediaPlayer.pause();
        countDownTimerStart.cancel();
        if (countDownTimerResume != null) {
            countDownTimerResume.cancel();
        }
    }

    private void playMusic(int position) {
        String path = "android.resource://" + getPackageName() + "/" + position;
        Uri uri = Uri.parse(path);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            sendTime.setAction("CheckPlay");
            countDownTimerStart = new CountDownTimer(mediaPlayer.getDuration(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    sendTime.putExtra(TIME_CURRENT, mediaPlayer.getCurrentPosition());
                    sendTime.putExtra(TIME_TOTAL, mediaPlayer.getDuration());
                    sendBroadcast(sendTime);
                    timeRemaining = millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    sendTime.putExtra(TIME_CURRENT, mediaPlayer.getCurrentPosition());
                    sendTime.putExtra(TIME_TOTAL, mediaPlayer.getDuration());
                    sendBroadcast(sendTime);
                }
            };
            countDownTimerStart.start();
        } catch (IOException e) {
            Log.d("", e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }
}
