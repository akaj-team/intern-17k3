package vn.asiantech.internship.ui.viewpager.service.controller;

import android.content.Context;
import android.content.Intent;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.MusicActivity;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.FunctionsUtil;

public class MusicController {
    private static final String ACTION_PAUSE = "paused";
    private static final String IS_PAUSE = "is_paused";
    private static Intent mIntent;

    private static void init(Context context) {
        mIntent = new Intent(context, MusicActivity.class);
        mIntent.setAction(ACTION_PAUSE);
    }

    public static void playControl(Context context) {
        sendMessage(context.getResources().getString(R.string.play));
        init(context);
        Constants.IS_SONG_PAUSED = false;
        mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
        context.sendBroadcast(mIntent);
    }

    public static void pauseControl(Context context) {
        sendMessage(context.getResources().getString(R.string.pause));
        init(context);
        Constants.IS_SONG_PAUSED = true;
        mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
        context.sendBroadcast(mIntent);
    }

    public static void nextControl(Context context) {
        boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning) {
            return;
        }
        if (Constants.SONGS_LIST.size() > 0) {
            if (Constants.SONG_POSITION < (Constants.SONGS_LIST.size() - 1)) {
                Constants.SONG_POSITION++;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            } else {
                Constants.SONG_POSITION = 0;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            }
        }
        Constants.IS_SONG_PAUSED = false;
        init(context);
        mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
        context.sendBroadcast(mIntent);
    }

    public static void previousControl(Context context) {
        boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning) {
            return;
        }
        if (Constants.SONGS_LIST.size() > 0) {
            if (Constants.SONG_POSITION > 0) {
                Constants.SONG_POSITION--;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            } else {
                Constants.SONG_POSITION = Constants.SONGS_LIST.size() - 1;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            }
        }
        Constants.IS_SONG_PAUSED = false;
        init(context);
        mIntent.putExtra(IS_PAUSE, Constants.IS_SONG_PAUSED);
        context.sendBroadcast(mIntent);
    }

    private static void sendMessage(String message) {
        try {
            Constants.PLAY_PAUSE_HANDLER.sendMessage(Constants.PLAY_PAUSE_HANDLER.obtainMessage(0, message));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
