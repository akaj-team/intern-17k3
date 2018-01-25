package vn.asiantech.internship.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;
import vn.asiantech.internship.ui.viewpager_tablayout.activity.PlayMusicActivity;

/**
 * Created by anh.quach on 1/18/18.
 * Service Music.
 */
public class MusicService extends Service {
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private ArrayList<Music> mListMusics = new ArrayList<>();
    private int mPos;
    RemoteViews notificationView;
    private Intent mIntent = new Intent();
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    Notification notification;
    Intent resultIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferences = getSharedPreferences(getResources().getString(R.string.key_save_data), Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        initNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case "sendData":
                        mListMusics = intent.getParcelableArrayListExtra(getString(R.string.key_list_data));
                        break;
                    case "start":
                        mPos = intent.getIntExtra(getString(R.string.key_position), 0);
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                        showNotification(mListMusics.get(mPos));
                        mEditor.putInt(getString(R.string.key_position), mPos);
                        mEditor.apply();
                        break;
                    case "play":
                        mMediaPlayer.start();
                        showNotification(mListMusics.get(mPos));
                        break;
                    case "pause":
                        mMediaPlayer.pause();
                        showNotification(mListMusics.get(mPos));
                        saveIsPlaying();
                        break;
                    case "next":
                        playNextSong();
                        showNotification(mListMusics.get(mPos));
                        break;
                    case "previous":
                        playPreviousSong();
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                        showNotification(mListMusics.get(mPos));
                        break;
                    case "pauseNotif":
                        mMediaPlayer.pause();
                        showNotification(mListMusics.get(mPos));
                        stopForeground(false);
                        saveIsPlaying();
                        sendIsPlayingBroadcast(false);
                        break;
                    case "playNotif":
                        mMediaPlayer.start();
                        startForeground(11, notification);
                        showNotification(mListMusics.get(mPos));
                        sendIsPlayingBroadcast(true);
                        break;
                    case "nextNotif":
                        playNextSong();
                        sendDataBroadcast(mPos);
                        showNotification(mListMusics.get(mPos));
                        sendIsPlayingBroadcast(true);
                        break;
                    case "previousNotif":
                        playPreviousSong();
                        sendDataBroadcast(mPos);
                        setMedia(mPos);
                        showNotification(mListMusics.get(mPos));
                        sendIsPlayingBroadcast(true);
                }
            }
        }
        return START_STICKY;
    }

    private void playNextSong() {
        mPos = (mPos + 1) % mListMusics.size();
        sendDataBroadcast(mPos);
        setMedia(mPos);
    }

    private void playPreviousSong() {
        if (mPos > 0) {
            mPos = mPos - 1;
        } else {
            mPos = mListMusics.size() - 1;
        }
    }

    private void setMedia(int position) {
        mMediaPlayer.reset();
        Uri mMediaPath = Uri.parse(getString(R.string.android_resource) + getPackageName() + getString(R.string.operator_divide) + mListMusics.get(position).getMusic());
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource(this, mMediaPath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playNextSong();
            }
        });
    }

    private void sendDataBroadcast(int position) {
        mIntent.setAction(getString(R.string.action_send_data));
        mIntent.putExtra(getString(R.string.key_music), mListMusics.get(position));
        sendBroadcast(mIntent);
    }

    private void sendIsPlayingBroadcast(Boolean playing) {
        mIntent.setAction(getString(R.string.action_send_is_playing));
        mIntent.putExtra(getString(R.string.key_is_playing), playing);
        sendBroadcast(mIntent);
    }

    private void saveIsPlaying() {
        mEditor = mPreferences.edit();
        mEditor.putBoolean(getString(R.string.key_is_playing), false);
        mEditor.apply();
    }

    private void initNotification() {
        resultIntent = new Intent(this, PlayMusicActivity.class);
        notificationView = new RemoteViews(this.getPackageName(), R.layout.notifiacation_play_music);
        pendingIntent(R.string.action_pause_notif, R.id.imgPauseNoti);
        pendingIntent(R.string.action_next_notif, R.id.imgNextNoti);
        pendingIntent(R.string.action_previous_notif, R.id.imgPreviousNoti);
        pendingIntent(R.string.action_play_notif, R.id.imgPlayNoti);
        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_music_note_red_400_18dp)
                .setContentIntent(
                        PendingIntent.getActivity(
                                this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)
                .setOngoing(false)
                .setContent(notificationView).build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification(Music music) {
        notificationView.setImageViewResource(R.id.imgAvatarNoti, music.getAvatar());
        notificationView.setTextViewText(R.id.tvTitleNoti, music.getTittle());
        notificationView.setTextViewText(R.id.tvArtistNoti, music.getArtist());
        if (mMediaPlayer.isPlaying()) {
            notificationView.setViewVisibility(R.id.imgPlayNoti, View.GONE);
            notificationView.setViewVisibility(R.id.imgPauseNoti, View.VISIBLE);
        } else {
            notificationView.setViewVisibility(R.id.imgPlayNoti, View.VISIBLE);
            notificationView.setViewVisibility(R.id.imgPauseNoti, View.GONE);
        }
        startForeground(11, notification);
    }

    private void pendingIntent(int action, int res) {
        Intent intentService = new Intent(this, MusicService.class);
        intentService.setAction(getString(action));
        PendingIntent stop = PendingIntent.getService(this, 0, intentService, 0);
        notificationView.setOnClickPendingIntent(res, stop);
    }
}
