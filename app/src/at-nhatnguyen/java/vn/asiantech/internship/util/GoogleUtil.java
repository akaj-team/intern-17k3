package vn.asiantech.internship.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by hoangnhat on 05/12/2017.
 * This class check app google photos installed
 */
public final class GoogleUtil {
    public static final String GOOGLE_PHOTO_PACKAGE = "com.google.android.apps.photos";

    private GoogleUtil() {
        // No-op
    }

    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO_PACKAGE, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
