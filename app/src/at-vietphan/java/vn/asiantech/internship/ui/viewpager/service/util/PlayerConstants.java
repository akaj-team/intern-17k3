package vn.asiantech.internship.ui.viewpager.service.util;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.ui.viewpager.service.MediaItem;

public class PlayerConstants {
	//List of Songs
	public static List<MediaItem> SONGS_LIST = new ArrayList<>();
	//song number which is playing right now from SONGS_LIST
	public static int SONG_NUMBER = 0;
	//song is playing or paused
	public static boolean IS_SONG_PAUSED = true;
	//handler for song changed(next, previous) defined in service(SongService)
	public static Handler SONG_CHANGE_HANDLER;
	//handler for song play/pause defined in service(SongService)
	public static Handler PLAY_PAUSE_HANDLER;
	//handler for showing song progress defined in Activities(MusicActivity, AudioPlayerActivity)
	public static Handler PROGRESSBAR_HANDLER;
}
