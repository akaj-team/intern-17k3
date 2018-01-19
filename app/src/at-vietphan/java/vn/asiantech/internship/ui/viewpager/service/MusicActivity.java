package vn.asiantech.internship.ui.viewpager.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.util.PlayerConstants;
import vn.asiantech.internship.ui.viewpager.service.util.UtilFunctions;

public class MusicActivity extends AppCompatActivity implements MediaItemAdapter.OnItemClickListener, OnClickListener {
    private static TextView mTvPlayingSong;
    private static ImageView mImgPause;
    private static ImageView mImgPlay;
    private static ImageView mImgNext;
    private static ImageView mImgPrevious;
    private static LinearLayout mLnPlayingSong;
    private MediaItemAdapter mMediaItemAdapter;
    private ImageView mImgStop;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mTvBufferDuration;
    private TextView mTvDuration;

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    public static void updateUI() {
        try {
            MediaItem data = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER);
            mTvPlayingSong.setText(data.getTitle() + " " + data.getArtist() + "-" + data.getAlbum());
            mLnPlayingSong.setVisibility(View.VISIBLE);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public static void changeButton() {
        if (PlayerConstants.IS_SONG_PAUSED) {
            mImgPause.setVisibility(View.GONE);
            mImgPlay.setVisibility(View.VISIBLE);
        } else {
            mImgPause.setVisibility(View.VISIBLE);
            mImgPlay.setVisibility(View.GONE);
        }
    }

    public static void changeUI() {
        updateUI();
        changeButton();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initViews();
        initListeners();
        initData();
        initAdapter();
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
            PlayerConstants.SONGS_LIST = UtilFunctions.listOfSongs(getApplicationContext());
        }
    }

    private void initAdapter() {
        mMediaItemAdapter = new MediaItemAdapter(PlayerConstants.SONGS_LIST, this);
        mRecyclerView.setAdapter(mMediaItemAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViews() {
        mTvPlayingSong = findViewById(R.id.tvNowPlaying);
        mImgPlay = findViewById(R.id.imgPlay);
        mImgPause = findViewById(R.id.imgPause);
        mImgNext = findViewById(R.id.imgNext);
        mImgPrevious = findViewById(R.id.imgPrevious);
        mProgressBar = findViewById(R.id.progressBar);
        mImgStop = findViewById(R.id.imgStop);
        mTvBufferDuration = findViewById(R.id.tvBufferDuration);
        mTvDuration = findViewById(R.id.tvDuration);
        mRecyclerView = findViewById(R.id.recyclerViewMusic);
        mLnPlayingSong = findViewById(R.id.lnPlayingSong);
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
            boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getApplicationContext());
            if (isServiceRunning) {
                updateUI();
            } else {
                mLnPlayingSong.setVisibility(View.GONE);
            }
            changeButton();
            PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Integer i[] = (Integer[]) msg.obj;
                    mTvBufferDuration.setText(UtilFunctions.getDuration(i[0]));
                    mTvDuration.setText(UtilFunctions.getDuration(i[1]));
                    mProgressBar.setProgress(i[2]);
                }
            };
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public void onItemClick(int position) {
        Log.d("TAG", "TAG Tapped INOUT(IN)");
        PlayerConstants.IS_SONG_PAUSED = false;
        PlayerConstants.SONG_NUMBER = position;
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getApplicationContext());
        if (!isServiceRunning) {
            Intent i = new Intent(getApplicationContext(), SongService.class);
            startService(i);
        } else {
            PlayerConstants.SONG_CHANGE_HANDLER.sendMessage(PlayerConstants.SONG_CHANGE_HANDLER.obtainMessage());
        }
        updateUI();
        changeButton();
        Log.d("TAG", "TAG Tapped INOUT(OUT)");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPlay:
                Controls.playControl(getApplicationContext());
                break;
            case R.id.imgPause:
                Controls.pauseControl(getApplicationContext());
                break;
            case R.id.imgNext:
                Controls.nextControl(getApplicationContext());
                break;
            case R.id.imgPrevious:
                Controls.previousControl(getApplicationContext());
                break;
            case R.id.imgStop:
                stopService(new Intent(getApplicationContext(), SongService.class));
                mLnPlayingSong.setVisibility(View.GONE);
        }
    }
}