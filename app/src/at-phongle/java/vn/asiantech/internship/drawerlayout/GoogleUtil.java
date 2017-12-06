package vn.asiantech.internship.drawerlayout;

import android.content.Context;
import android.content.pm.PackageManager;

public final class GoogleUtil {
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";
    public static boolean isPackageInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
