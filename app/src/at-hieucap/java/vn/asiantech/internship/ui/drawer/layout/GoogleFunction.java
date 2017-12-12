package vn.asiantech.internship.ui.drawer.layout;

import android.content.Context;
import android.content.pm.PackageManager;

final class GoogleFunction {
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";

    static boolean isPackageInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
