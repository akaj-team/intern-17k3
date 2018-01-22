package vn.asiantech.internship.viewpagerandtablelayout.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vn.asiantech.internship.viewpagerandtablelayout.service.PlayMusicService;
import vn.asiantech.internship.viewpagerandtablelayout.ui.PlayMusicActivity;

/**
 * Created by TienHuynh on 22/01/2018.
 * AsianTech Co., Ltd
 */
public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
//            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
//            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
//                return;
//            }
//            switch (keyEvent.getKeyCode()) {
//                case KeyEvent.KEYCODE_HEADSETHOOK:
//                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
//                    if (!Constants.IS_SONG_PAUSED) {
//                        MusicController.pauseControl(context);
//                    } else {
//                        MusicController.playControl(context);
//                    }
//                    break;
//                case KeyEvent.KEYCODE_MEDIA_PLAY:
//                    break;
//                case KeyEvent.KEYCODE_MEDIA_PAUSE:
//                    break;
//                case KeyEvent.KEYCODE_MEDIA_STOP:
//                    break;
//                case KeyEvent.KEYCODE_MEDIA_NEXT:
//                    MusicController.nextControl(context);
//                    break;
//                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
//                    MusicController.previousControl(context);
//            }
//        } else {
        if (intent.getAction().equals(PlayMusicService.NOTIFY_PLAY)) {
        } else if (intent.getAction().equals(PlayMusicService.NOTIFY_PAUSE)) {
        } else if (intent.getAction().equals(PlayMusicService.NOTIFY_NEXT)) {
        } else if (intent.getAction().equals(PlayMusicService.NOTIFY_DELETE)) {
            context.stopService(new Intent(context, PlayMusicService.class));
            Intent intentStop = new Intent(context, PlayMusicActivity.class);
            intentStop.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentStop);
        } else if (intent.getAction().equals(PlayMusicService.NOTIFY_PREVIOUS)) {
        }
    }
}
