package vn.asiantech.internship.viewpagerandtablelayout.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created at 2017
 * Created by jackty on 19/12/2017.
 */

public class ScreenUtil {
    private ScreenUtil() {
        // No-opp
    }

    /**
     * This method is used to get height of screen
     *
     * @param context is current context
     * @return return height screen in pixel
     */
    public static int getHeightScreen(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dimension);
        return dimension.heightPixels;
    }

    /**
     * This method is used to get width of screen
     *
     * @param context is current context
     * @return return width of screen in pixel
     */
    public static int getWidthScreen(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dimension);
        return dimension.widthPixels;
    }
}
