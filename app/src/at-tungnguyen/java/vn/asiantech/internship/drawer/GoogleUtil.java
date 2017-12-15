package vn.asiantech.internship.drawer;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * GoogleUtil
 */
public final class GoogleUtil {
    public static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";

    private GoogleUtil() {
        // No-op
    }

    /**
     * isGooglePhotosInstalled
     *
     * @param context
     * @return
     */
    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTOS_PACKAGE_NAME, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
