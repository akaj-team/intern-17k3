package vn.asiantech.internship.ui.viewpager.service.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.controller.MusicController;
import vn.asiantech.internship.ui.viewpager.service.models.Song;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.FunctionsUtil;

public class MediaService extends Service {
    public static final String NOTIFY_PLAY = "play";
    public static final String NOTIFY_PAUSE = "pause";
    public static final String NOTIFY_PREVIOUS = "previous";
    public static final String NOTIFY_NEXT = "next";
    public static final String NOTIFY_DELETE = "delete";

    public static final String ACTION_PAUSE = "paused";
    public static final String IS_PAUSE = "is_paused";
    public static final String ACTION_STOP = "stopped";

    private Timer mTimer;
    private MediaPlayer mMediaPlayer;
    private Intent mIntentSendBroadcast;
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mMediaPlayer != null) {
                int progress = (mMediaPlayer.getCurrentPosition() * 100) / mMediaPlayer.getDuration();
                Integer i[] = new Integer[3];
                i[0] = mMediaPlayer.getCurrentPosition();
                i[1] = mMediaPlayer.getDuration();
                i[2] = progress;
                try {
                    Constants.mProgressbarHandler.sendMessage(Constants.mProgressbarHandler.obtainMessage(0, i));
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mTimer = new Timer();
        mIntentSendBroadcast = new Intent();
        mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MusicController.nextControl(getApplicationContext());
            }
        });
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        try {
            if (Constants.mSongs.size() <= 0) {
                Constants.mSongs = FunctionsUtil.getListSongs(getApplicationContext());
            }
            Song song = Constants.mSongs.get(Constants.mSongPosition);
            String songPath = song.getPath();
            playSong(songPath);
            newNotification(false);
            Constants.mSongChangeHandler = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Song song = Constants.mSongs.get(Constants.mSongPosition);
                    String songPath = song.getPath();
                    newNotification(false);
                    playSong(songPath);
                    mIntentSendBroadcast.setAction(ACTION_PAUSE);
                    mIntentSendBroadcast.putExtra(IS_PAUSE, false);
                    sendBroadcast(mIntentSendBroadcast);
                    return false;
                }
            });

            Constants.mPlayPauseHandler = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    String message = (String) msg.obj;

                    boolean isPause = false;
                    if (mMediaPlayer == null) {
                        return false;
                    }
                    if (message.equalsIgnoreCase(getResources().getString(R.string.play))) {
                        // isPause=false;
                        mMediaPlayer.start();
                    } else if (message.equalsIgnoreCase(getResources().getString(R.string.pause))) {
                        isPause = true;
                        mMediaPlayer.pause();
                    }
                    newNotification(isPause);
                    mIntentSendBroadcast.setAction(ACTION_PAUSE);
                    mIntentSendBroadcast.putExtra(IS_PAUSE, isPause);
                    sendBroadcast(mIntentSendBroadcast);
                    return false;
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }

    /**
     * Play song, Update Lockscreen fields
     *
     * @param songPath songPath
     */
    private void playSong(String songPath) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(songPath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mTimer.scheduleAtFixedRate(new MainTask(), 0, 100);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void newNotification(boolean isPause) {
        String songName = Constants.mSongs.get(Constants.mSongPosition).getTitle();
        String composer = Constants.mSongs.get(Constants.mSongPosition).getComposer();
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_notification);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_music).setContentTitle(songName).build();
        setListeners(remoteViews);
        notification.contentView = remoteViews;
        if (isPause) {
            notification.contentView.setViewVisibility(R.id.imgPause, View.GONE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.VISIBLE);
        } else {
            notification.contentView.setViewVisibility(R.id.imgPause, View.VISIBLE);
            notification.contentView.setViewVisibility(R.id.imgPlay, View.GONE);
        }
        notification.contentView.setTextViewText(R.id.tvSongName, songName);
        notification.contentView.setTextViewText(R.id.tvAlbumName, composer);
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(77, notification);
    }

    /**
     * Notification click listeners
     *
     * @param view view
     */
    public void setListeners(RemoteViews view) {
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_PLAY);
        PendingIntent pPrevious = PendingIntent.getBroadcast(getApplicationContext(), 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPrevious, pPrevious);
        PendingIntent pDelete = PendingIntent.getBroadcast(getApplicationContext(), 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnDelete, pDelete);
        PendingIntent pPause = PendingIntent.getBroadcast(getApplicationContext(), 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPause, pPause);
        PendingIntent pNext = PendingIntent.getBroadcast(getApplicationContext(), 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgNext, pNext);
        PendingIntent pPlay = PendingIntent.getBroadcast(getApplicationContext(), 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.imgPlay, pPlay);
    }

    /**
     * Send message from mTimer
     *
     * @author jonty.ankit
     */
    private class MainTask extends TimerTask {
        public void run() {
            mHandler.sendEmptyMessage(0);
        }
    }
}
