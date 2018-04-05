package vn.asiantech.internship.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 22/01/2018.
 */

public class NotificationBroadcast extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
         Intent mIntent = new Intent(context, MusicPlayService.class);
        if (intent.getAction().equals(MusicPlayService.NOTIFY_PLAY)) {
            mIntent.setAction(MusicPlayService.ACTION_RESUME);
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_PAUSE)) {
            mIntent.setAction(MusicPlayService.ACTION_PAUSE);
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_NEXT)) {
            mIntent.setAction(MusicPlayService.ACTION_NEXT);
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_PREVIOUS)) {
            mIntent.setAction(MusicPlayService.ACTION_PREVIOUS);
            context.startService(mIntent);
        } else if (intent.getAction().equals(MusicPlayService.NOTIFY_DELETE)) {
            context.stopService(mIntent);
        }
    }
}
