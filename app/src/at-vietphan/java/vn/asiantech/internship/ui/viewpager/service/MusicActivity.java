package vn.asiantech.internship.ui.viewpager.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.adapter.SongAdapter;
import vn.asiantech.internship.ui.viewpager.service.controller.MusicController;
import vn.asiantech.internship.ui.viewpager.service.models.Song;
import vn.asiantech.internship.ui.viewpager.service.service.MediaService;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.FunctionsUtil;

public class MusicActivity extends AppCompatActivity implements SongAdapter.OnItemClickListener, OnClickListener {
    private static final String ACTION_PAUSE = "paused";
    private static final String ACTION_STOP = "stopped";
    private static final String IS_PAUSE = "is_paused";
    private LinearLayout mLnPlayingSong;
    private TextView mTvPlayingSong;
    private ImageView mImgPlay;
    private ImageView mImgPause;
    private ImageView mImgStop;
    private ImageView mImgPrevious;
    private ImageView mImgNext;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mTvBufferDuration;
    private TextView mTvDuration;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), ACTION_PAUSE)) {
                boolean isPaused = intent.getBooleanExtra(IS_PAUSE, false);
                onChangeBtnUI(isPaused);
                onChangeLayoutUI();
            } else if (TextUtils.equals(intent.getAction(), ACTION_STOP)) {
                mLnPlayingSong.setVisibility(View.GONE);
            }
        }
    };

    private void onChangeLayoutUI() {
        try {
            Song song = Constants.SONGS_LIST.get(Constants.SONG_POSITION);
            mTvPlayingSong.setText(song.getTitle().concat(" ")
                    .concat(song.getArtist()).concat("-").concat(song.getAlbum()));
            mLnPlayingSong.setVisibility(View.VISIBLE);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void onChangeBtnUI(boolean pause) {
        if (pause) {
            mImgPause.setVisibility(View.GONE);
            mImgPlay.setVisibility(View.VISIBLE);
        } else {
            mImgPause.setVisibility(View.VISIBLE);
            mImgPlay.setVisibility(View.GONE);
        }
    }

//    public static void onChangeBtnUI() {
//        if (Constants.IS_SONG_PAUSED) {
//            mImgPause.setVisibility(View.GONE);
//            mImgPlay.setVisibility(View.VISIBLE);
//        } else {
//            mImgPause.setVisibility(View.VISIBLE);
//            mImgPlay.setVisibility(View.GONE);
//        }
//    }

    private void updateAllUI() {
        onChangeLayoutUI();
        onChangeBtnUI(Constants.IS_SONG_PAUSED);
//        onChangeBtnUI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initViews();
        initData();
        initAdapter();
        initListeners();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PAUSE);
        intentFilter.addAction(ACTION_STOP);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void initViews() {
        mLnPlayingSong = findViewById(R.id.lnPlayingSong);
        mTvPlayingSong = findViewById(R.id.tvNowPlaying);
        mImgPlay = findViewById(R.id.imgPlay);
        mImgPause = findViewById(R.id.imgPause);
        mImgStop = findViewById(R.id.imgStop);
        mImgPrevious = findViewById(R.id.imgPrevious);
        mImgNext = findViewById(R.id.imgNext);
        mRecyclerView = findViewById(R.id.recyclerViewMusic);
        mProgressBar = findViewById(R.id.progressBar);
        mTvBufferDuration = findViewById(R.id.tvBufferDuration);
        mTvDuration = findViewById(R.id.tvDuration);
    }

    private void initData() {
        mTvPlayingSong.setSelected(true);
        mProgressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Constants.SONGS_LIST = FunctionsUtil.getListSongs(getApplicationContext());
        }
    }

    private void initAdapter() {
        SongAdapter songAdapter = new SongAdapter(Constants.SONGS_LIST, this);
        mRecyclerView.setAdapter(songAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initListeners() {
        mImgPlay.setOnClickListener(this);
        mImgPause.setOnClickListener(this);
        mImgStop.setOnClickListener(this);
        mImgPrevious.setOnClickListener(this);
        mImgNext.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onResume() {
        super.onResume();
        try {
            boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), getApplicationContext());
            if (isServiceRunning) {
                updateAllUI();
            } else {
                mLnPlayingSong.setVisibility(View.GONE);
            }
            Constants.PROGRESSBAR_HANDLER = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Integer i[] = (Integer[]) msg.obj;
                    mTvBufferDuration.setText(FunctionsUtil.getDuration(i[0]));
                    mTvDuration.setText(FunctionsUtil.getDuration(i[1]));
                    mProgressBar.setProgress(i[2]);
                }
            };
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public void onItemClick(int position) {
        Constants.IS_SONG_PAUSED = false;
        Constants.SONG_POSITION = position;
        boolean isServiceRunning = FunctionsUtil.isServiceRunning(MediaService.class.getName(), getApplicationContext());
        if (!isServiceRunning) {
            Intent intent = new Intent(getApplicationContext(), MediaService.class);
            startService(intent);
        } else {
            Constants.SONG_CHANGE_HANDLER.sendMessage(Constants.SONG_CHANGE_HANDLER.obtainMessage());
        }
        updateAllUI();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPlay:
                MusicController.playControl(getApplicationContext());
                break;
            case R.id.imgPause:
                MusicController.pauseControl(getApplicationContext());
                break;
            case R.id.imgNext:
                MusicController.nextControl(getApplicationContext());
                break;
            case R.id.imgPrevious:
                MusicController.previousControl(getApplicationContext());
                break;
            case R.id.imgStop:
                stopService(new Intent(getApplicationContext(), MediaService.class));
                mLnPlayingSong.setVisibility(View.GONE);
        }
    }
}
