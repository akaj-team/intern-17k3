package vn.asiantech.internship.ui.drawerlayout;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by hoangnhat on 05/12/2017.
 * This class check app google photos installed
 */

final class GoogleUtil {
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";

    private GoogleUtil() {
    }

    static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
