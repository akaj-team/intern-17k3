package vn.asiantech.internship.ui.viewpager.service.controller;

import android.content.Context;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.UtilFunctions;

public class MusicController {

    public static void playControl(Context context) {
        sendMessage(context.getResources().getString(R.string.play));
    }

    public static void pauseControl(Context context) {
        sendMessage(context.getResources().getString(R.string.pause));
    }

    public static void nextControl(Context context) {
        boolean isServiceRunning = UtilFunctions.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning)
            return;
        if (Constants.SONGS_LIST.size() > 0) {
            if (Constants.SONG_NUMBER < (Constants.SONGS_LIST.size() - 1)) {
                Constants.SONG_NUMBER++;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            } else {
                Constants.SONG_NUMBER = 0;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            }
        }
        Constants.IS_SONG_PAUSED = false;
    }

    public static void previousControl(Context context) {
        boolean isServiceRunning = UtilFunctions.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning)
            return;
        if (Constants.SONGS_LIST.size() > 0) {
            if (Constants.SONG_NUMBER > 0) {
                Constants.SONG_NUMBER--;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            } else {
                Constants.SONG_NUMBER = Constants.SONGS_LIST.size() - 1;
                Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
            }
        }
        Constants.IS_SONG_PAUSED = false;
    }

    private static void sendMessage(String message) {
        try {
            Constants.PLAY_PAUSE_HANDLER.sendMessage(Constants.PLAY_PAUSE_HANDLER.obtainMessage(0, message));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
