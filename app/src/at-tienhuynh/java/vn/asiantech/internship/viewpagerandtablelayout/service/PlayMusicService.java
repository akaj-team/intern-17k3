package vn.asiantech.internship.viewpagerandtablelayout.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.models.Music;
import vn.asiantech.internship.viewpagerandtablelayout.utils.MusicAction;

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
    private final Intent sendBroadCastIntent = new Intent();
    private boolean mIsPlay = false;

    @Override
    public void onCreate() {
        super.onCreate();
        // init Media
        initMedia();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case MusicAction.DATA:
                    mMusicLists = intent.getParcelableArrayListExtra(MusicAction.DATA_LIST);
                    break;
                case MusicAction.PLAY:
                    mIsPlay = true;
                    sendSingStatus(mIsPlay);
                    resumeMusic();
                    newNotification();
                    break;
                case MusicAction.PAUSE:
                    mIsPlay = false;
                    sendSingStatus(mIsPlay);
                    pauseMusic();
                    newNotification();
                    break;
                case MusicAction.POSITION:
                    mIsPlay = true;
                    mPosition = intent.getIntExtra(MusicAction.POSITION_LIST, 0);
                    sendSingStatus(mIsPlay);
                    playSongMusic(mPosition);
                    newNotification();
                    break;
                case MusicAction.NEXT:
                    mPosition += 1;
                    if (mPosition < mMusicLists.size()) {
                        playSongMusic(mPosition);
                    } else {
                        mPosition = mMusicLists.size() - 1;
                    }
                    newNotification();
                    break;
                case MusicAction.PREVIOUS:
                    mPosition -= 1;
                    if (mPosition >= 0) {
                        playSongMusic(mPosition);
                    } else {
                        mPosition = 0;
                    }
                    newNotification();
                    break;
                case MusicAction.STOP:
                    mMediaPlayer.stop();
                    break;
                case MusicAction.MOVE_SEEK_BAR:
                    int timeMoving = intent.getIntExtra(MusicAction.TIME_MOVING, 0);
                    mMediaPlayer.seekTo(timeMoving);
                    break;
            }
        }
        return START_STICKY;
    }

    /**
     * Send status of isPlay is true or phone to Activity
     */
    private void sendSingStatus(boolean isPlay) {
        sendBroadCastIntent.setAction(MusicAction.PLAY_STATUS);
        sendBroadCastIntent.putExtra(MusicAction.IS_PLAY, isPlay);
        sendBroadcast(sendBroadCastIntent);
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
        sendBroadCastIntent.setAction(MusicAction.SEND_TIME);
        mCountTimeResumeSong = new CountDownTimer(mTimeResuming, 1000) {
            @Override
            public void onTick(long l) {
                sendBroadCastIntent.putExtra(MusicAction.NAME_SONG_POSITION, mPosition);
                sendBroadCastIntent.putExtra(MusicAction.CURRENT_TIME, mMediaPlayer.getCurrentPosition());
                sendBroadCastIntent.putExtra(MusicAction.TOTAL_TIME, mMediaPlayer.getDuration());
                sendBroadcast(sendBroadCastIntent);
                mTimeResuming = l;
            }

            @Override
            public void onFinish() {
                sendBroadCastIntent.putExtra(MusicAction.CURRENT_TIME, mMediaPlayer.getCurrentPosition());
                sendBroadCastIntent.putExtra(MusicAction.TOTAL_TIME, mMediaPlayer.getDuration());
                sendBroadcast(sendBroadCastIntent);
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
            sendBroadCastIntent.setAction(MusicAction.SEND_TIME);
            mCountTimeStartSong = new CountDownTimer(mMediaPlayer.getDuration(), 1000) {
                @Override
                public void onTick(long l) {
                    sendBroadCastIntent.putExtra(MusicAction.NAME_SONG_POSITION, mPosition);
                    sendBroadCastIntent.putExtra(MusicAction.CURRENT_TIME, mMediaPlayer.getCurrentPosition());
                    sendBroadCastIntent.putExtra(MusicAction.TOTAL_TIME, mMediaPlayer.getDuration());
                    sendBroadcast(sendBroadCastIntent);
                    mTimeResuming = l;
                }

                @Override
                public void onFinish() {
                    sendBroadCastIntent.putExtra(MusicAction.CURRENT_TIME, mMediaPlayer.getCurrentPosition());
                    sendBroadCastIntent.putExtra(MusicAction.TOTAL_TIME, mMediaPlayer.getDuration());
                    sendBroadcast(sendBroadCastIntent);
                }
            };
            mCountTimeStartSong.start();
        } catch (IOException e) {
            Log.d("XXX", e.getMessage());
        }
    }

    private void newNotification() {
        String songName = mMusicLists.get(mPosition).getName();
        String albumName = mMusicLists.get(mPosition).getSinger();
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.item_custom_notification);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_music_item)
                .setContentTitle(songName).build();
        setListeners(remoteViews);
        notification.contentView = remoteViews;
        if (!mIsPlay) {
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
     *
     * @param view view
     */
    public void setListeners(RemoteViews view) {
        Intent previous = new Intent(MusicAction.PREVIOUS);
        Intent delete = new Intent(MusicAction.STOP);
        Intent pause = new Intent(MusicAction.PAUSE);
        Intent next = new Intent(MusicAction.NEXT);
        Intent play = new Intent(MusicAction.PLAY);
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

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //no -opp
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
    }
}
