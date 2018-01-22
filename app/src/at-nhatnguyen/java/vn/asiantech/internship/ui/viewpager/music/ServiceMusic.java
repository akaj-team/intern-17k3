package vn.asiantech.internship.ui.viewpager.music;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Song;

/**
 * Created by hoangnhat on 17/01/2018.
 * Class service
 */

public class ServiceMusic extends Service {
    public static final String NOTIFY_PREVIOUS = "previous";
    public static final String NOTIFY_DELETE = "delete";
    public static final String NOTIFY_PAUSE = "pause";
    public static final String NOTIFY_RESUME = "resume";
    public static final String NOTIFY_NEXT = "next";

    public static final String ACTION_PLAY = "Play";
    public static final String ACTION_RESUME = "Resume";
    public static final String ACTION_STOP = "Stop";
    public static final String ACTION_PAUSE = "Pause";
    public static final String ACTION_MOVE = "Move";
    public static final String ACTION_SEND_DATA = "SendData";
    public static final String ACTION_NEXT = "Next";
    public static final String ACTION_PREVIOUS = "Previous";

    public static final String POSITION = "Position";

    private static final String TIME_CURRENT = "CurrentTime";
    private static final String TIME_TOTAL = "TotalTime";
    private MediaPlayer mMediaPlayer;
    private CountDownTimer mCountDownTimerStart;
    private CountDownTimer mCountDownTimerResume;
    //    private Intent sendTime;
    private Intent sendInformationSong;
    private long timeRemaining;
    private boolean isPause;
    private ArrayList<Song> mSongs;
    private List<Integer> mListSong;
    private int songPlay;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        sendInformationSong = new Intent();
        timeRemaining = 0;
        isPause = false;
        mSongs = new ArrayList<>();
        mListSong = new ArrayList<>();
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
                    case "check":
                        checkRunSong();
                        break;
                    case ACTION_SEND_DATA:
                        mSongs = intent.getParcelableArrayListExtra("ListSong");
                        Log.d("xxx", "" + mSongs.size());
                        break;
                    case ACTION_PLAY:
                        isPause = false;
                        int position = intent.getIntExtra(POSITION, 0);
                        mListSong.add(position);
                        playMusic(position);
                        break;
                    case ACTION_MOVE:
                        int a = intent.getIntExtra("Move", 0);
                        mMediaPlayer.seekTo(a);
                        break;
                    case ACTION_RESUME:
                        isPause = false;
                        resumeMusic();
                        newNotification();
                        break;
                    case ACTION_STOP:
                        stopMusic();
                        break;
                    case ACTION_PAUSE:
                        isPause = true;
                        pauseMusic();
                        newNotification();
                        break;
                    case ACTION_NEXT:
                        nextMusic();
                        break;
                    case ACTION_PREVIOUS:
                        previousMusic();
                }
            }
        }
        return START_STICKY;
    }

    private void previousMusic() {
        if (mListSong.size() >= 2) {
            mListSong.remove(mListSong.size() - 1);
            int songPrevious = mListSong.get(mListSong.size() - 1);
            playMusic(songPrevious);
        } else {
            playMusic(0);
        }
    }

    private void nextMusic() {
        Random r = new Random();
        int songNext = r.nextInt(mSongs.size());
        playMusic(songNext);
        mListSong.add(songNext);
    }

    private void stopMusic() {
        mMediaPlayer.stop();
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        if (mCountDownTimerResume != null) {
            mCountDownTimerResume.cancel();
        }
    }

    private void resumeMusic() {
        mMediaPlayer.start();
        sendInformationSong.setAction("CheckPlay");
        mCountDownTimerResume = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                sendBroadcast(sendInformationSong);
                timeRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                sendBroadcast(sendInformationSong);
            }
        };
        mCountDownTimerResume.start();
    }

    private void pauseMusic() {
        mMediaPlayer.pause();
        mCountDownTimerStart.cancel();
        if (mCountDownTimerResume != null) {
            mCountDownTimerResume.cancel();
        }
    }

    private void playMusic(int position) {
        songPlay = position;
        newNotification();
        sendInformationSong.setAction("Information");
        sendInformationSong.putExtra("Information_song", mSongs.get(position));
        sendBroadcast(sendInformationSong);
        String path = "android.resource://" + getPackageName() + "/" + mSongs.get(position).getResource();
        Uri uri = Uri.parse(path);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(this, uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            sendInformationSong.setAction("CheckPlay");
            mCountDownTimerStart = new CountDownTimer(mMediaPlayer.getDuration(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                    sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                    sendBroadcast(sendInformationSong);
                    timeRemaining = millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                    sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                    sendBroadcast(sendInformationSong);
                }
            };
            mCountDownTimerStart.start();
        } catch (IOException e) {
            Log.d("", e.getMessage());
        }
    }

    private void checkRunSong() {
        if (mMediaPlayer.isPlaying()) {
            sendInformationSong.setAction("CheckSing");
            sendInformationSong.putExtra("Information_song", mSongs.get(songPlay));
            sendBroadcast(sendInformationSong);
            if (mCountDownTimerStart != null) {
                sendInformationSong.setAction("CheckPlay");
                mCountDownTimerStart = new CountDownTimer(mMediaPlayer.getDuration(), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                        sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                        sendBroadcast(sendInformationSong);
                        timeRemaining = millisUntilFinished;
                    }

                    @Override
                    public void onFinish() {
                        sendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                        sendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                        sendBroadcast(sendInformationSong);
                    }
                };
                mCountDownTimerStart.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        if (mCountDownTimerResume != null) {
            mCountDownTimerResume.cancel();
        }
        mMediaPlayer.stop();
        super.onDestroy();
    }

    private void newNotification() {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.item_notification);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_plate)
                .setContentTitle(mSongs.get(songPlay).getName()).build();
        setListeners(remoteViews);
        notification.contentView = remoteViews;
        if (isPause) {
            notification.contentView.setViewVisibility(R.id.imgPause, View.GONE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.VISIBLE);
        } else {
            notification.contentView.setViewVisibility(R.id.imgPause, View.VISIBLE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.GONE);
        }
        notification.contentView.setTextViewText(R.id.tvSongName, mSongs.get(songPlay).getName());
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(77, notification);
    }

    public void setListeners(RemoteViews view) {
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_RESUME);

        PendingIntent pPrevious = PendingIntent.getBroadcast(getApplicationContext(), 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPrevious, pPrevious);

        PendingIntent pDelete = PendingIntent.getBroadcast(getApplicationContext(), 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgDelete, pDelete);

        PendingIntent pPause = PendingIntent.getBroadcast(getApplicationContext(), 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPause, pPause);

        PendingIntent pNext = PendingIntent.getBroadcast(getApplicationContext(), 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgNext, pNext);

        PendingIntent pPlay = PendingIntent.getBroadcast(getApplicationContext(), 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPlay, pPlay);
    }
}
