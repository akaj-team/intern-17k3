package vn.asiantech.internship.ui.viewpager.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hoangnhat on 22/01/2018.
 */

public class NotificationBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mIntentSendAction = new Intent(context, ServiceMusic.class);
        if (intent.getAction().equals(ServiceMusic.NOTIFY_RESUME)) {
            mIntentSendAction.setAction(ServiceMusic.ACTION_RESUME);
            context.startService(mIntentSendAction);
        } else if (intent.getAction().equals(ServiceMusic.NOTIFY_PAUSE)) {
            mIntentSendAction.setAction(ServiceMusic.ACTION_PAUSE);
            context.startService(mIntentSendAction);
        } else if (intent.getAction().equals(ServiceMusic.NOTIFY_NEXT)) {
            mIntentSendAction.setAction(ServiceMusic.ACTION_NEXT);
            context.startService(mIntentSendAction);
        } else if (intent.getAction().equals(ServiceMusic.NOTIFY_DELETE)) {
//            mIntentSendAction.setAction(ServiceMusic.ACTION_STOP);
//            context.startService(mIntentSendAction);
            context.stopService(new Intent(context, ServiceMusic.class));
//            Intent intentStop = new Intent(context, MusicActivity.class);
//            intentStop.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intentStop);
        } else if (intent.getAction().equals(ServiceMusic.NOTIFY_PREVIOUS)) {
            mIntentSendAction.setAction(ServiceMusic.ACTION_PREVIOUS);
            context.startService(mIntentSendAction);
        }
    }
}
