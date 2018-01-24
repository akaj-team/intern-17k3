package vn.asiantech.internship.viewpagerandtablelayout.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.adapters.MusicAdapter;
import vn.asiantech.internship.viewpagerandtablelayout.models.Music;
import vn.asiantech.internship.viewpagerandtablelayout.service.PlayMusicService;
import vn.asiantech.internship.viewpagerandtablelayout.utils.MusicAction;

/**
 * Play Music Activity to play music
 */
public class PlayMusicActivity extends AppCompatActivity implements MusicAdapter.onItemClick, View.OnClickListener {

    private static final String MY_PREFS_NAME = "checkShow";
    private static final String PREFS_CHECK = "check";
    private SeekBar mSeekBarMusic;
    private ImageView mImgPlay;
    private TextView mTvTimeSongBottomPlay;
    private ImageView mImgExitPlayBottom;
    private ImageView mImgNextSong;
    private ImageView mImgPreviousSong;
    private RecyclerView mRecyclerViewPlayMusic;
    private ArrayList<Music> mMusicLists;
    private LinearLayout mLlPlayBottom;
    private TextView mTvNameSongBottomPlay;
    private boolean mIsPlay;
    private Intent mIntent;
    private int mPosition;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case MusicAction.SEND_TIME:
                    int current = intent.getIntExtra(MusicAction.CURRENT_TIME, 0);
                    int total = intent.getIntExtra(MusicAction.TOTAL_TIME, 0);
                    int position = intent.getIntExtra(MusicAction.NAME_SONG_POSITION, 0);
                    setNameBottomPlay(position);
                    mSeekBarMusic.setProgress(current);
                    mSeekBarMusic.setMax(total);
                    mTvTimeSongBottomPlay.setText(getString(R.string.time_song, convertTime(current), convertTime(total)));
                    mSeekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            // no-opp
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            // no-opp
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            int timeMoving = seekBar.getProgress();
                            mIntent.setAction(MusicAction.MOVE_SEEK_BAR);
                            mIntent.putExtra(MusicAction.TIME_MOVING, timeMoving);
                            startService(mIntent);
                        }
                    });
                    break;
                case MusicAction.PLAY_STATUS:
                    mIsPlay = intent.getBooleanExtra(MusicAction.IS_PLAY, false);
                    if (mIsPlay) {
                        mImgPlay.setImageResource(R.drawable.ic_pause_white_24dp);

                    } else {
                        mImgPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // init Data
        initData();
        // init views
        initViews();
        //send first status
        sendFirstStatus();
        // init RecyclerView
        initRecyclerView();
        // init Listener
        initListeners();
        // send list data to service
        sendData();
        // register Broad cast
        registerBroadcast();
        // set show panel
        showBottomPanel(isShowBottomPanel());
    }

    private void sendFirstStatus() {
        mIntent.setAction("firstStatus");
        startService(mIntent);
    }

    private void initViews() {
        mRecyclerViewPlayMusic = findViewById(R.id.recyclerViewMusic);
        mLlPlayBottom = findViewById(R.id.llPlayBottom);
        mImgExitPlayBottom = findViewById(R.id.imgExitPlayBottom);
        mTvNameSongBottomPlay = findViewById(R.id.tvNameSongBottomSheet);
        mImgPlay = findViewById(R.id.imgPlayMusic);
        mTvTimeSongBottomPlay = findViewById(R.id.tvSongTime);
        mImgNextSong = findViewById(R.id.imgNextSong);
        mImgPreviousSong = findViewById(R.id.imgPreviousSong);
        mSeekBarMusic = findViewById(R.id.seekBar);
        mIntent = new Intent(PlayMusicActivity.this, PlayMusicService.class);
        // marquee tv song name
        mTvNameSongBottomPlay.setSelected(true);
    }

    private void initData() {
        mMusicLists = new ArrayList<>();
        mMusicLists.add(new Music("Havana", "Trump", R.raw.the_spectre, "0:30"));
        mMusicLists.add(new Music("Until you", "Oba", R.raw.thefatrat, "0:22"));
        mMusicLists.add(new Music("Waiting love", "Jack", R.raw.nguoi_la_oi, "3:37"));
    }

    private void initRecyclerView() {
        MusicAdapter musicAdapter = new MusicAdapter(mMusicLists, this);
        mRecyclerViewPlayMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPlayMusic.setAdapter(musicAdapter);
    }

    private void initListeners() {
        mImgExitPlayBottom.setOnClickListener(this);
        mImgPlay.setOnClickListener(this);
        mImgNextSong.setOnClickListener(this);
        mImgPreviousSong.setOnClickListener(this);
        mImgNextSong.setOnClickListener(this);
    }

    /**
     * Send data to service
     */
    private void sendData() {
        mIntent.setAction(MusicAction.DATA);
        mIntent.putParcelableArrayListExtra(MusicAction.DATA_LIST, mMusicLists);
        startService(mIntent);
    }

    /**
     * show bottom panel
     *
     * @param checkShow checkShow
     */
    private void showBottomPanel(boolean checkShow) {
        if (checkShow) {
            mLlPlayBottom.setVisibility(View.VISIBLE);
        } else {
            mLlPlayBottom.setVisibility(View.GONE);
        }
    }

    /**
     * set share preference
     *
     * @param check check panel is show or not
     */
    private void setSharePreference(boolean check) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREFS_CHECK, check);
        editor.apply();
    }

    /**
     * get share preference
     */
    private boolean isShowBottomPanel() {
        boolean check;
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        check = prefs.getBoolean(PREFS_CHECK, false);
        return check;
    }

    @Override
    public void onItemClickListener(int potion) {
        mIsPlay = true;
        setSharePreference(mIsPlay);
        showBottomPanel(isShowBottomPanel());
        mPosition = potion;
        //set name song to Bottom Play
        setNameBottomPlay(potion);
        mIntent.setAction(MusicAction.POSITION);
        mIntent.putExtra(MusicAction.POSITION_LIST, potion);
        startService(mIntent);
        mImgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
    }

    private void setNameBottomPlay(int position) {
        mTvNameSongBottomPlay.setText(mMusicLists.get(position).getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgExitPlayBottom:
                mIntent.setAction(MusicAction.STOP);
                startService(mIntent);
                mIsPlay = false;
                setSharePreference(mIsPlay);
                showBottomPanel(isShowBottomPanel());
                break;
            case R.id.imgPlayMusic:
                if (!mIsPlay) {
                    // play
                    mIntent.setAction(MusicAction.PLAY);
                    mImgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                    mIsPlay = true;
                    setSharePreference(mIsPlay);
                    startService(mIntent);
                } else {
                    // pause
                    mIntent.setAction(MusicAction.PAUSE);
                    mImgPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    mIsPlay = false;
                    setSharePreference(mIsPlay);
                    startService(mIntent);
                }
                break;
            case R.id.imgNextSong:
                mIntent.setAction(MusicAction.NEXT);
                startService(mIntent);
                mImgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                mIsPlay = true;
                setSharePreference(mIsPlay);
                mPosition += 1;
                if (mPosition < mMusicLists.size()) {
                    setNameBottomPlay(mPosition);
                } else {
                    mPosition = mMusicLists.size() - 1;
                }
                break;
            case R.id.imgPreviousSong:
                mIntent.setAction(MusicAction.PREVIOUS);
                startService(mIntent);
                mImgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                mIsPlay = true;
                setSharePreference(mIsPlay);
                mPosition -= 1;
                if (mPosition >= 0) {
                    setNameBottomPlay(mPosition);
                } else {
                    mPosition = 0;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    /**
     * Register Broadcast
     */
    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicAction.SEND_TIME);
        intentFilter.addAction(MusicAction.PLAY_STATUS);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    /**
     * Convert time from service send to activity
     *
     * @param millisecond millisecond
     * @return string time
     */
    private String convertTime(int millisecond) {
        int minute = millisecond / 60000;
        int second = (millisecond / 1000) % 60;
        return minute + ":" + second;
    }
}
