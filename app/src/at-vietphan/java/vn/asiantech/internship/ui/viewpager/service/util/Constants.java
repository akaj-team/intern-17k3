package vn.asiantech.internship.ui.viewpager.service.util;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.ui.viewpager.service.models.Song;

public class Constants {
    //List of Songs
    public static List<Song> SONGS_LIST = new ArrayList<>();
    //Song number which is playing right now from SONGS_LIST
    public static int SONG_INDEX = 0;
    //Song is playing or paused
    public static boolean IS_SONG_PAUSED = true;
    //Handler for song changed(next, previous) defined in service(MediaService)
    public static Handler SONG_CHANGE_HANDLER;
    //Handler for song play/pause defined in service(MediaService)
    public static Handler PLAY_PAUSE_HANDLER;
    //Handler for showing song progress defined in Activities(MusicActivity, AudioPlayerActivity)
    public static Handler PROGRESSBAR_HANDLER;
    //State app is running or not running
    public static boolean IS_RUNNING = false;
}
