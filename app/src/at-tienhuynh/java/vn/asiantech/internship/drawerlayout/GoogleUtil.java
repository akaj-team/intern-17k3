package vn.asiantech.internship.drawerlayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Created at 2017
 * Created by jackty on 05/12/2017.
 */

public final class GoogleUtil {
    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(DrawerLayoutActivity.GOOGLE_PHOTOS_PACKAGE_NAME, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, context.getString(R.string.not_installed_google_photos), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
