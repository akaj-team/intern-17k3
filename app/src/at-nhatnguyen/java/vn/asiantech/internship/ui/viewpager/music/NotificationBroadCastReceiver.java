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
            context.stopService(new Intent(context, ServiceMusic.class));
        } else if (intent.getAction().equals(ServiceMusic.NOTIFY_PREVIOUS)) {
            mIntentSendAction.setAction(ServiceMusic.ACTION_PREVIOUS);
            context.startService(mIntentSendAction);
        }
    }
}
