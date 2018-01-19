package vn.asiantech.internship.notifcation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;
import vn.asiantech.internship.ui.viewpager_tablayout.activity.PlayMusicActivity;

public class PlayMusicNotification {
    private static final String NOTIFICATION_TAG = "NewMessage";

    public static void notify(final Context context, final int number, Music music) {
        Intent resultIntent = new Intent(context, PlayMusicActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_new_message)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true);
        RemoteViews notificationView = new RemoteViews(context.getPackageName(), R.layout.notifiacation_play_music);
        notificationView.setImageViewResource(R.id.imgAvatarNoti, R.drawable.img_avatar);
        notificationView.setTextViewText(R.id.tvTitleNoti, music.getTittle());
        notificationView.setTextViewText(R.id.tvArtistNoti, music.getSinger());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder = builder.setContent(notificationView);
        } else {
            builder = builder.setSmallIcon(android.R.drawable.ic_menu_gallery);
        }
        notify(context, builder.build());
    }

    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        }
    }

    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.cancel(NOTIFICATION_TAG, 0);
        }
    }
}
