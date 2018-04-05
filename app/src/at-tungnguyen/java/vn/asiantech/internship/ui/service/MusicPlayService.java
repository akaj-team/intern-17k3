package vn.asiantech.internship.ui.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;


/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class MusicPlayService extends Service {
    private MediaPlayer mMediaPlayer;
    private ArrayList<Music> mMusicList;
    private int mPositonNotification;
    public static final String NOTIFY_PREVIOUS = "previous";
    public static final String NOTIFY_DELETE = "delete";
    public static final String NOTIFY_PAUSE = "pause";
    public static final String NOTIFY_PLAY = "play";
    public static final String NOTIFY_NEXT = "next";

    public static final String SEND_INFO_MUSIC = "infomusic";

    public static final String POSITION = "Position";

    public static final String ACTION_PLAY = "Play";
    public static final String ACTION_RESUME = "Resume";
    public static final String ACTION_STOP = "Stop";
    public static final String ACTION_PAUSE = "Pause";
    public static final String ACTION_NEXT = "Next";
    public static final String ACTION_PREVIOUS = "Previous";
    public static final String CHECK_RUN = "CheckRun";
    public static final String INFO_SINNGING = "singing";
    private boolean isPause = false;
    private List<Integer> mLists;
    private Notification mNotification;
    private Intent mIntent;

    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isPause = false;
        mMediaPlayer = new MediaPlayer();
        mLists = new ArrayList<>();
        mIntent = new Intent();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    isPause = false;
                    int Position = intent.getIntExtra(POSITION, 0);
                    onPlayMusic(Position);
                    newNotification();
                    break;
                case ACTION_PAUSE:
                    isPause = true;
                    onPause();
                    newNotification();
                    break;
                case ACTION_NEXT:
                    onNext();
                    newNotification();
                    break;
                case ACTION_PREVIOUS:
                    onPrevious();
                    newNotification();
                    break;
                case CHECK_RUN:
                    checkRunMusic();
                    break;
                case ACTION_RESUME:
                    isPause = false;
                    onResume();
                    newNotification();
                    break;
                case ACTION_STOP:
                    break;
                case "data":
                    mMusicList = intent.getParcelableArrayListExtra("array");
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void checkRunMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.isPlaying();
            sendActionCheckRun(true);

        }
    }

    private void onPause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    private void onNext() {
        Random random = new Random();
        int nextMusic = random.nextInt(mMusicList.size());
        onPlayMusic(nextMusic);
        mLists.add(nextMusic);
    }

    private void onPrevious() {
        Random random = new Random();
        int nextMusic = random.nextInt(mMusicList.size());
        onPlayMusic(nextMusic);
        mLists.add(nextMusic);
    }

    private void onResume() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    private void onPlayMusic(int position) {
        mPositonNotification = position;
        if (position < mMusicList.size()) {
            sendInfoMusic(position);
            mIntent.putExtra(INFO_SINNGING, true);
            String path = "android.resource://" + getPackageName() + "/" + mMusicList.get(position).getUriMusic();
            Uri uri = Uri.parse(path);
            try {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(this, uri);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
    }

    private void sendInfoMusic(int position) {
        Intent intentInfo = new Intent();
        intentInfo.setAction(SEND_INFO_MUSIC);
        intentInfo.putExtra("name", getNameMusic(position));
        intentInfo.putExtra("single", getSingle(position));
        intentInfo.putExtra("sing", true);
        sendBroadcast(intentInfo);
    }

    private void sendActionCheckRun(boolean checkRun) {
        Intent intentCheckRun = new Intent();
        intentCheckRun.setAction(MusicActivity.CHECK_RUN);
        intentCheckRun.putExtra("true", checkRun);
        sendBroadcast(intentCheckRun);
    }

    private String getNameMusic(int position) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(mMusicList.get(position).getUriMusic());
        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        return mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
    }

    private String getSingle(int position) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(mMusicList.get(position).getUriMusic());
        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        return mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
    }

    /**
     * Notification
     */
    private void newNotification() {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.item_notification);
        mNotification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_logo_music)
                .setContentTitle(mMusicList.get(mPositonNotification).getNameMusic()).build();

        setListeners(remoteViews);
        mNotification.contentView = remoteViews;
        if (isPause) {
            mNotification.contentView.setViewVisibility(R.id.imgPause, View.GONE);
            mNotification.contentView.setViewVisibility(R.id.imgPlay, View.VISIBLE);
        } else {
            mNotification.contentView.setViewVisibility(R.id.imgPause, View.VISIBLE);
            mNotification.contentView.setViewVisibility(R.id.imgPlay, View.GONE);
        }
        mNotification.contentView.setTextViewText(R.id.tvSongName, mMusicList.get(mPositonNotification).getNameMusic());
        mNotification.contentView.setTextViewText(R.id.tvAlbumName, mMusicList.get(mPositonNotification).getSingle());
        mNotification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(77, mNotification);
    }

    /**
     * Notification click listeners
     *
     * @param view view
     */
    private void setListeners(RemoteViews view) {
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
