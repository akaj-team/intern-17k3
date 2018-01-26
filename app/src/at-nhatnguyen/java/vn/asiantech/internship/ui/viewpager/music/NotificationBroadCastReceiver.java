package vn.asiantech.internship.ui.viewpager.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by hoangnhat on 22/01/2018.
 * Broadcast receiver
 */

public class NotificationBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentSendAction = new Intent(context, ServiceMusic.class);
        if (TextUtils.equals(intent.getAction(),ServiceMusic.NOTIFY_RESUME)) {
            intentSendAction.setAction(ServiceMusic.ACTION_RESUME);
            context.startService(intentSendAction);
        } else if (TextUtils.equals(intent.getAction(),ServiceMusic.NOTIFY_PAUSE)) {
            intentSendAction.setAction(ServiceMusic.ACTION_PAUSE);
            context.startService(intentSendAction);
        } else if (TextUtils.equals(intent.getAction(),ServiceMusic.NOTIFY_NEXT)) {
            intentSendAction.setAction(ServiceMusic.ACTION_NEXT);
            context.startService(intentSendAction);
        } else if (TextUtils.equals(intent.getAction(),ServiceMusic.NOTIFY_DELETE)) {
            context.stopService(new Intent(context, ServiceMusic.class));
        } else if (TextUtils.equals(intent.getAction(),ServiceMusic.NOTIFY_PREVIOUS)) {
            intentSendAction.setAction(ServiceMusic.ACTION_PREVIOUS);
            context.startService(intentSendAction);
        }
    }
}
