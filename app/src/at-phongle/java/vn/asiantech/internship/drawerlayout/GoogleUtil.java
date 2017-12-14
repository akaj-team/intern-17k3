package vn.asiantech.internship.drawerlayout;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by phongle on 11/12/2560.
 * Google Util Class
 */
public final class GoogleUtil {
    public static final String GOOGLE_PHOTO = "com.google.android.apps.photos";

    public static boolean isPackageInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
