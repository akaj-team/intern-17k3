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
import android.support.v4.app.NotificationCompat;
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
    private static final String ACTION_PAUSE = "paused";
    private static final String IS_PAUSE = "is_paused";
    private Timer mTimer;
    private MediaPlayer mMediaPlayer;
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
                    Constants.PROGRESSBAR_HANDLER.sendMessage(Constants.PROGRESSBAR_HANDLER.obtainMessage(0, i));
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    };
    private Intent mIntent;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mTimer = new Timer();
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
            if (Constants.SONGS_LIST.size() <= 0) {
                Constants.SONGS_LIST = FunctionsUtil.getListSongs(getApplicationContext());
            }
            Song song = Constants.SONGS_LIST.get(Constants.SONG_POSITION);
            String songPath = song.getPath();
            playSong(songPath);
            newNotification();
            Constants.SONG_CHANGE_HANDLER = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Song song = Constants.SONGS_LIST.get(Constants.SONG_POSITION);
                    String songPath = song.getPath();
                    newNotification();
                    playSong(songPath);
                    mIntent = new Intent();
                    Constants.IS_SONG_PAUSED = false;
                    mIntent.setAction(ACTION_PAUSE);
                    mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
                    sendBroadcast(mIntent);
//                    MusicActivity.updateAllUI();
                    return false;
                }
            });

            Constants.PLAY_PAUSE_HANDLER = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    String message = (String) msg.obj;
                    if (mMediaPlayer == null) {
                        return false;
                    }
                    if (message.equalsIgnoreCase(getResources().getString(R.string.play))) {
                        Constants.IS_SONG_PAUSED = false;
                        mMediaPlayer.start();
                    } else if (message.equalsIgnoreCase(getResources().getString(R.string.pause))) {
                        Constants.IS_SONG_PAUSED = true;
                        mMediaPlayer.pause();
                    }
                    newNotification();
                    mIntent = new Intent();
                    mIntent.setAction(ACTION_PAUSE);
                    mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
                    sendBroadcast(mIntent);
//                    MusicActivity.onChangeBtnUI();
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

    private void newNotification() {
        String songName = Constants.SONGS_LIST.get(Constants.SONG_POSITION).getTitle();
        String composer = Constants.SONGS_LIST.get(Constants.SONG_POSITION).getComposer();
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_notification);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_music).setContentTitle(songName).build();
        setListeners(remoteViews);
        notification.contentView = remoteViews;
        if (Constants.IS_SONG_PAUSED) {
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
