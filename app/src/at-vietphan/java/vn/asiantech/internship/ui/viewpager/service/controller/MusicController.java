package vn.asiantech.internship.ui.viewpager.service.controller;

import android.content.Context;
import android.content.Intent;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.MusicActivity;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.FunctionsUtil;

public class MusicController {
    private static Intent mIntent;

    private static void init(Context context) {
        mIntent = new Intent(context, MusicActivity.class);
        mIntent.setAction(MediaService.ACTION_PAUSE);
    }

    public static void playControl(Context context) {
        sendMessage(context.getResources().getString(R.string.play));
        init(context);
        Constants.mIsSongPaused = false;
        mIntent.putExtra(MediaService.IS_PAUSE, Constants.mIsSongPaused);
        context.sendBroadcast(mIntent);
    }

    public static void pauseControl(Context context) {
        sendMessage(context.getResources().getString(R.string.pause));
        init(context);
        Constants.mIsSongPaused = true;
        mIntent.putExtra(MediaService.IS_PAUSE, Constants.mIsSongPaused);
        context.sendBroadcast(mIntent);
    }

    public static void nextControl(Context context) {
        boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning) {
            return;
        }
        if (Constants.mSongs.size() > 0) {
            if (Constants.mSongPosition < (Constants.mSongs.size() - 1)) {
                Constants.mSongPosition++;
                Constants.mSongChangeHandler.sendMessage(Constants.mSongChangeHandler.obtainMessage());
            } else {
                Constants.mSongPosition = 0;
                Constants.mSongChangeHandler.sendMessage(Constants.mSongChangeHandler.obtainMessage());
            }
        }
        Constants.mIsSongPaused = false;
        init(context);
        mIntent.putExtra(MediaService.IS_PAUSE, Constants.mIsSongPaused);
        context.sendBroadcast(mIntent);
    }

    public static void previousControl(Context context) {
        boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), context);
        if (!isServiceRunning) {
            return;
        }
        if (Constants.mSongs.size() > 0) {
            if (Constants.mSongPosition > 0) {
                Constants.mSongPosition--;
                Constants.mSongChangeHandler.sendMessage(Constants.mSongChangeHandler.obtainMessage());
            } else {
                Constants.mSongPosition = Constants.mSongs.size() - 1;
                Constants.mSongChangeHandler.sendMessage(Constants.mSongChangeHandler.obtainMessage());
            }
        }
        Constants.mIsSongPaused = false;
        init(context);
        mIntent.putExtra(MediaService.IS_PAUSE, Constants.mIsSongPaused);
        context.sendBroadcast(mIntent);
    }

    private static void sendMessage(String message) {
        try {
            Constants.mPlayPauseHandler.sendMessage(Constants.mPlayPauseHandler.obtainMessage(0, message));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
