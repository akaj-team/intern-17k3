package vn.asiantech.internship.viewpagerandtablelayout.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vn.asiantech.internship.viewpagerandtablelayout.service.PlayMusicService;
import vn.asiantech.internship.viewpagerandtablelayout.utils.MusicAction;

/**
 * Created by TienHuynh on 22/01/2018.
 * AsianTech Co., Ltd
 */
public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentSendAction = new Intent(context, PlayMusicService.class);
        if (intent.getAction().equals(MusicAction.PLAY)) {
            intentSendAction.setAction("play");
            context.startService(intentSendAction);
        } else if (intent.getAction().equals(MusicAction.PAUSE)) {
            intentSendAction.setAction("pause");
            context.startService(intentSendAction);
        } else if (intent.getAction().equals(MusicAction.NEXT)) {
            intentSendAction.setAction("next");
            context.startService(intentSendAction);
        } else if (intent.getAction().equals(MusicAction.STOP)) {
            context.stopService(intentSendAction);
        } else if (intent.getAction().equals(MusicAction.PREVIOUS)) {
            intentSendAction.setAction("previous");
            context.startService(intentSendAction);
        }
    }
}
