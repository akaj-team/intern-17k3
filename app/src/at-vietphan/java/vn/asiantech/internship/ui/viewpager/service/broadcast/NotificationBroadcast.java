package vn.asiantech.internship.ui.viewpager.service.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import vn.asiantech.internship.ui.viewpager.service.MusicActivity;
import vn.asiantech.internship.ui.viewpager.service.controller.MusicController;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;

public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN){
                return;
            }
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_HEADSETHOOK:
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    if (!Constants.IS_SONG_PAUSED) {
                        MusicController.pauseControl(context);
                    } else {
                        MusicController.playControl(context);
                    }
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    MusicController.nextControl(context);
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    MusicController.previousControl(context);
            }
        } else {
            if (intent.getAction().equals(MediaService.NOTIFY_PLAY)) {
                MusicController.playControl(context);
            } else if (intent.getAction().equals(MediaService.NOTIFY_PAUSE)) {
                MusicController.pauseControl(context);
            } else if (intent.getAction().equals(MediaService.NOTIFY_NEXT)) {
                MusicController.nextControl(context);
            } else if (intent.getAction().equals(MediaService.NOTIFY_DELETE)) {
                context.stopService(new Intent(context, MediaService.class));
                Intent intentStop = new Intent(context, MusicActivity.class);
                intentStop.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentStop);
            } else if (intent.getAction().equals(MediaService.NOTIFY_PREVIOUS)) {
                MusicController.previousControl(context);
            }
        }
    }
}
