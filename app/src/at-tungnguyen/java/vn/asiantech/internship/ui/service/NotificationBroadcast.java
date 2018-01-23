package vn.asiantech.internship.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import vn.asiantech.internship.model.Music;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 22/01/2018.
 */

public class NotificationBroadcast extends BroadcastReceiver {
    private ArrayList<Music> musicList;
    private Intent mIntent;

    public void onReceive(Context context, Intent intent) {
//        musicList = intent.getParcelableArrayListExtra("array");
        if (intent.getAction().equals(MusicPlayService.NOTIFY_PLAY)) {
            mIntent = new Intent(context, MusicPlayService.class);
            mIntent.setAction("play");
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_PAUSE)) {
            mIntent = new Intent(context, MusicPlayService.class);
            mIntent.setAction("pause");
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_NEXT)) {
            mIntent = new Intent(context, MusicPlayService.class);
            mIntent.setAction("next");
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_PREVIOUS)) {
            mIntent = new Intent(context, MusicPlayService.class);
            mIntent.setAction("previous");
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_DELETE)) {
            mIntent = new Intent(context, MusicPlayService.class);
            context.stopService(mIntent);
        }
    }
}
