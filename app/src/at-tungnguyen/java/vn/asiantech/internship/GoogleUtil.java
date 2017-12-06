package vn.asiantech.internship;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by tungnguyen on 05/12/2017.
 */

public class GoogleUtil {
    public static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";

    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTOS_PACKAGE_NAME, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
