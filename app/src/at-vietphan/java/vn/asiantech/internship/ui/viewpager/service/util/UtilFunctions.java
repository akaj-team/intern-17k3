package vn.asiantech.internship.ui.viewpager.service.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import vn.asiantech.internship.ui.viewpager.service.models.Song;

public class UtilFunctions {

    /**
     * Check if service is running or not
     *
     * @param serviceName serviceName
     * @param context     context
     * @return isServiceRunning false or true
     */
    public static boolean isServiceRunning(String serviceName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Read the songs present in external storage
     *
     * @param context context
     * @return listOfSongs
     */
    public static ArrayList<Song> listOfSongs(Context context) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor c = context.getContentResolver().query(uri, null, MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);
        ArrayList<Song> listOfSongs = new ArrayList<>();
        c.moveToFirst();
        while (c.moveToNext()) {
            Song song = new Song();
            String title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            long duration = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
            long albumId = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            String composer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.COMPOSER));

            song.setTitle(title);
            song.setAlbum(album);
            song.setArtist(artist);
            song.setDuration(duration);
            song.setPath(data);
            song.setAlbumId(albumId);
            song.setComposer(composer);
            listOfSongs.add(song);
        }
        c.close();
        return listOfSongs;
    }

    /**
     * Convert milliseconds into time hh:mm:ss
     *
     * @param milliseconds milliseconds
     * @return time in String
     */
    public static String getDuration(long milliseconds) {
        long sec = (milliseconds / 1000) % 60;
        long min = (milliseconds / (60 * 1000)) % 60;
        long hour = milliseconds / (60 * 60 * 1000);
        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;
        String time;
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }
}
