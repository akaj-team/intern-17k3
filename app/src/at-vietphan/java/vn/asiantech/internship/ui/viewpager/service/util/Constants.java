package vn.asiantech.internship.ui.viewpager.service.util;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.ui.viewpager.service.models.Song;

public class Constants {
    public static List<Song> mSongs = new ArrayList<>();
    public static int mSongPosition = 0;
    public static boolean mIsSongPaused = true;
    public static Handler mSongChangeHandler;
    public static Handler mPlayPauseHandler;
    public static Handler mProgressbarHandler;
    private static final Constants mConstants = new Constants();

    private Constants() {
        // No-op
    }

    public static Constants getInstance() {
        return mConstants;
    }
}
