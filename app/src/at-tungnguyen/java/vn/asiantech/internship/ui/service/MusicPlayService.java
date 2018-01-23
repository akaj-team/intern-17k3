package vn.asiantech.internship.ui.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;


/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class MusicPlayService extends Service {
    private MediaPlayer mMediaPlayer;
    private ArrayList<Music> mMusicList;
    private int mPosition = 0;
    private final IBinder musicBind = new MusicBinder();
    public static final String NOTIFY_PREVIOUS = "previous";
    public static final String NOTIFY_DELETE = "delete";
    public static final String NOTIFY_PAUSE = "pause";
    public static final String NOTIFY_PLAY = "play";
    public static final String NOTIFY_NEXT = "next";
    private static boolean isPause = false;

    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        mMusicList = intent.getParcelableArrayListExtra("array");
        return musicBind;
    }

    class MusicBinder extends Binder {
        MusicPlayService getService() {
            return MusicPlayService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public void onNext(int position) {
        mPosition++;
        if (mPosition < mMusicList.size()) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), mMusicList.get(position).getUriMusic());
                mMediaPlayer.start();
                getNameMusic(position);
            }
        } else if (mPosition > mMusicList.size()) {
            Toast.makeText(this, R.string.tv_end, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case "play":
                    onPlay();
                    isPause = false;
                    newNotification();
                    break;
                case "pause":
                    onPause();
                    isPause = true;
                    newNotification();
                    break;
                case "next":
                    onNext(mPosition);
                    isPause = false;
                    newNotification();
                    break;
                case "previous":
                    onPrevious(mPosition);
                    isPause = false;
                    newNotification();
                    break;
                case "delete":
                    stopService(intent);
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void onPrevious(int position) {
        mPosition--;
        if (mPosition < mMusicList.size()) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            } else {
                mPosition = mMusicList.size();
            }
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), mMusicList.get(position).getUriMusic());
            mMediaPlayer.start();
        }
    }

    public void onPlay() {
        mMediaPlayer.start();
    }

    public String getNameMusic(int position) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(mMusicList.get(position).getUriMusic());
        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        return mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
    }

    public String getSingle(int position) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(mMusicList.get(position).getUriMusic());
        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        return mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
    }

    public void onStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    public void onPause() {
        mMediaPlayer.pause();
    }

    /**
     * OnItemMusicClick
     */
    public void onItemMusicClick(int position) {
        onStop();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), mMusicList.get(position).getUriMusic());
        mMediaPlayer.start();
        mPosition = position;
        newNotification();
    }

    /**
     * Notification
     */
    private void newNotification() {
        String songName = mMusicList.get(mPosition).getNameMusic();
        String albumName = mMusicList.get(mPosition).getSingle();
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification_mediacontroller);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_logo_music)
                .setContentTitle(songName).build();
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
        notification.contentView.setTextViewText(R.id.tvAlbumName, albumName);
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(77, notification);
    }

    /**
     * Notification click listeners
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
}
