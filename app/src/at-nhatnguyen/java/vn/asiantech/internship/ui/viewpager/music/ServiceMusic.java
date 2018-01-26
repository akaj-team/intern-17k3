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
    public static final String ACTION_CHECK_RUN = "CheckRun";

    public static final String POSITION = "Position";
    public static final String MOVE = "Move";
    public static final String INFORMATION_SONG = "Information_song";
    public static final String INFORMATION_SING = "Information_sing";
    public static final String CHECK_RUN = "Run";

    public static final String TIME_CURRENT = "CurrentTime";
    public static final String TIME_TOTAL = "TotalTime";
    private MediaPlayer mMediaPlayer;
    private CountDownTimer mCountDownTimerStart;
    private Intent mIntentSendInformationSong;
    private long mTimeRemaining;
    private boolean mIsPause;
    private ArrayList<Song> mSongs;
    private List<Integer> mListSong;
    private int mSongPlay;

    @Override
    public void onCreate() {
        super.onCreate();
        mIntentSendInformationSong = new Intent();
        mTimeRemaining = 0;
        mIsPause = false;
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
                    case ACTION_CHECK_RUN:
                        checkRunSong();
                        break;
                    case ACTION_SEND_DATA:
                        mSongs = intent.getParcelableArrayListExtra(MusicActivity.KEY_LIST);
                        break;
                    case ACTION_PLAY:
                        mIsPause = false;
                        int position = intent.getIntExtra(POSITION, 0);
                        mListSong.add(position);
                        playMusic(position);
                        break;
                    case ACTION_MOVE:
                        int a = intent.getIntExtra(MOVE, 0);
                        mCountDownTimerStart.cancel();
                        createCountDownTimer(mMediaPlayer.getDuration() - a);
                        mMediaPlayer.seekTo(a);
                        break;
                    case ACTION_PAUSE:
                        mIsPause = true;
                        pauseMusic();
                        newNotification();
                        break;
                    case ACTION_RESUME:
                        mIsPause = false;
                        resumeMusic();
                        newNotification();
                        break;
                    case ACTION_STOP:
                        stopMusic();
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
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        if (mListSong.size() >= 2) {
            mListSong.remove(mListSong.size() - 1);
            int songPrevious = mListSong.get(mListSong.size() - 1);
            playMusic(songPrevious);
        } else {
            playMusic(0);
        }
    }

    private void nextMusic() {
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        Random r = new Random();
        int songNext = r.nextInt(mSongs.size());
        playMusic(songNext);
        mListSong.add(songNext);
    }

    private void playMusic(int position) {
        mSongPlay = position;
        newNotification();
        sendActionCheckRun(true);
        sendActionInformationSong(position);
        mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_SING);
        mIntentSendInformationSong.putExtra(INFORMATION_SING, true);
        mIntentSendInformationSong.putExtra(INFORMATION_SONG, mSongs.get(position));
        sendBroadcast(mIntentSendInformationSong);
        String path = "android.resource://" + getPackageName() + "/" + mSongs.get(position).getResource();
        Uri uri = Uri.parse(path);
        try {
            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            } else {
                mMediaPlayer.reset();
            }
            mMediaPlayer.setDataSource(this, uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            createCountDownTimer(mMediaPlayer.getDuration());
        } catch (IOException e) {
            Log.d("", e.getMessage());
        }
    }

    private void stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        sendActionCheckSing(false);
        sendActionCheckRun(false);
        mMediaPlayer = null;
    }

    private void resumeMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            sendActionCheckRun(true);
            createCountDownTimer(mTimeRemaining);
        } else {
            mMediaPlayer = new MediaPlayer();
            nextMusic();
        }
    }

    private void pauseMusic() {
        mMediaPlayer.pause();
        sendActionCheckRun(false);
    }

    private void createCountDownTimer(long time) {
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
        }
        mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_TIME);
        mCountDownTimerStart = new CountDownTimer(time, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mIntentSendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                mIntentSendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                sendBroadcast(mIntentSendInformationSong);
                mTimeRemaining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                mIntentSendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                mIntentSendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                sendBroadcast(mIntentSendInformationSong);
                nextMusic();
            }
        };
        mCountDownTimerStart.start();
    }

    private void checkRunSong() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                sendActionCheckRun(true);
                mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_SING);
                mIntentSendInformationSong.putExtra(INFORMATION_SONG, mSongs.get(mSongPlay));
                mIntentSendInformationSong.putExtra(INFORMATION_SING, true);
                sendBroadcast(mIntentSendInformationSong);
                createCountDownTimer(mMediaPlayer.getDuration() - mMediaPlayer.getCurrentPosition());
            } else {
                sendActionCheckSing(true);
                mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_TIME);
                mIntentSendInformationSong.putExtra(TIME_CURRENT, mMediaPlayer.getCurrentPosition());
                mIntentSendInformationSong.putExtra(TIME_TOTAL, mMediaPlayer.getDuration());
                sendBroadcast(mIntentSendInformationSong);
            }
        } else {
            mMediaPlayer = new MediaPlayer();
            sendActionCheckSing(false);
        }
    }

    private void sendActionCheckSing(boolean check) {
        mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_SING);
        mIntentSendInformationSong.putExtra(INFORMATION_SING, check);
        sendBroadcast(mIntentSendInformationSong);
    }

    private void sendActionInformationSong(int position) {
        mIntentSendInformationSong.setAction(MusicActivity.ACTION_INFORMATION_SONG);
        mIntentSendInformationSong.putExtra(INFORMATION_SONG, mSongs.get(position));
        sendBroadcast(mIntentSendInformationSong);
    }

    private void sendActionCheckRun(boolean check) {
        mIntentSendInformationSong.setAction(MusicActivity.ACTION_CHECK_RUN);
        mIntentSendInformationSong.putExtra(CHECK_RUN, check);
        sendBroadcast(mIntentSendInformationSong);
    }

    @Override
    public void onDestroy() {
        if (mCountDownTimerStart != null) {
            mCountDownTimerStart.cancel();
            mCountDownTimerStart = null;
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
        checkRunSong();
        super.onDestroy();
    }

    private void newNotification() {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.item_notification);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_plate)
                .setContentTitle(mSongs.get(mSongPlay).getName()).build();
        setListeners(remoteViews);
        notification.contentView = remoteViews;
        if (mIsPause) {
            notification.contentView.setViewVisibility(R.id.imgPause, View.GONE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.VISIBLE);
        } else {
            notification.contentView.setViewVisibility(R.id.imgPause, View.VISIBLE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.GONE);
        }
        notification.contentView.setTextViewText(R.id.tvSongName, mSongs.get(mSongPlay).getName());
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
