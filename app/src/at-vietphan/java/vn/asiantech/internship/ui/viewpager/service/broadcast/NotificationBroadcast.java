package vn.asiantech.internship.ui.viewpager.service.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vn.asiantech.internship.ui.viewpager.service.controller.MusicController;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;

public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MediaService.NOTIFY_PLAY)) {
            MusicController.playControl(context);
        } else if (intent.getAction().equals(MediaService.NOTIFY_PAUSE)) {
            MusicController.pauseControl(context);
        } else if (intent.getAction().equals(MediaService.NOTIFY_NEXT)) {
            MusicController.nextControl(context);
        } else if (intent.getAction().equals(MediaService.NOTIFY_PREVIOUS)) {
            MusicController.previousControl(context);
        } else if (intent.getAction().equals(MediaService.NOTIFY_DELETE)) {
            context.stopService(new Intent(context, MediaService.class));
            intent = new Intent();
            intent.setAction("stopped");
            context.sendBroadcast(intent);
        }
    }
}
