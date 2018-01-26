package vn.asiantech.internship.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener, View.OnClickListener {
    public static final String CHECK_RUN = "CheckRun";
    private ArrayList<Music> mMusicList;
    private RecyclerView mRecyclerViewMusic;
    private LinearLayout mLlMusic;
    private Button mBtnPlay;
    private Button mBtnNextMusic;
    private Button mBtnPrevious;
    private TextView mTvNameMusic;
    private TextView mTvSingle;
    boolean isSelected = true;
    private MusicPlayService mMusicPlayService;
    private Intent mIntent;
    private boolean isSing = false;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case MusicPlayService.SEND_INFO_MUSIC:
                        mTvNameMusic.setText(intent.getStringExtra("name"));
                        mTvSingle.setText(intent.getStringExtra("single"));
                        isSing = intent.getBooleanExtra("sing", false);
                        setBtnPlayPause(isSing);
                        break;
                    case CHECK_RUN:
                        isSing = intent.getBooleanExtra("true", false);
                        setBtnPlayPause(isSing);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mMusicList = new ArrayList<>();
        initViews();
        initAdapter();
        initData();
        initListener();
        mIntent = new Intent(getApplicationContext(), MusicPlayService.class);
        mIntent.putParcelableArrayListExtra("array", mMusicList);
        mIntent.setAction("data");
        startService(mIntent);
//        bindService(mIntent, musicConnection, Context.BIND_AUTO_CREATE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicPlayService.SEND_INFO_MUSIC);
        intentFilter.addAction(CHECK_RUN);
        registerReceiver(mBroadcastReceiver, intentFilter);
        sendCheckRun();
    }

    /**
     * initViews MusicActivity
     */
    private void initViews() {
        mBtnPrevious = findViewById(R.id.btnPreviousMusic);
        mBtnNextMusic = findViewById(R.id.btnNextMusic);
        mLlMusic = findViewById(R.id.llToolMusic);
        mRecyclerViewMusic = findViewById(R.id.recyclerviewMusic);
        mBtnPlay = findViewById(R.id.btnPlayMusic);
        mTvNameMusic = findViewById(R.id.tvNameMusic);
        mTvSingle = findViewById(R.id.tvSingle);
    }

    /**
     * initAdapter Music Activity
     */
    private void initAdapter() {
        mMusicList = new ArrayList<>();
        MusicAdapter mMusicAdapter = new MusicAdapter(mMusicList, this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mMusicAdapter);
    }

    /**
     * initData MusicActivity
     */
    private void initData() {
        mMusicList.add(new Music("Người lạ ơi", "Karrik", R.raw.music_2));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", R.raw.music3));
        mMusicList.add(new Music("Ánh Nắng Của Anh", "Đức Phúc", R.raw.music_anhnangcuaanh));
        mMusicList.add(new Music("Sống xa anh chẵng dễ dàng", "Bảo Anh", R.raw.music_baoanh));
        mMusicList.add(new Music("Người lạ ơi", "Karrik", R.raw.music_2));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", R.raw.music3));
        mMusicList.add(new Music("Ánh Nắng Của Anh", "Đức Phúc", R.raw.music_anhnangcuaanh));
        mMusicList.add(new Music("Sống xa anh chẵng dễ dàng", "Bảo Anh", R.raw.music_baoanh));
    }

    /**
     * initListener of MusicActivity
     */
    private void initListener() {
        mBtnPrevious.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
        mBtnNextMusic.setOnClickListener(this);
    }

    /**
     * set Button Play or Pause
     */
    private void setBtnPlayPause(boolean checkSing) {
        if (checkSing) {
            mBtnPlay.setBackground(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
        } else {
            mBtnPlay.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        }
    }

    /**
     * onItemClick Music
     */
    @Override
    public void onItemClick(Music music, int position) {
        if (isSelected) {
            mLlMusic.setVisibility(View.VISIBLE);
        } else {
            mLlMusic.setVisibility(View.INVISIBLE);
        }
        Intent intentClick = new Intent(MusicActivity.this, MusicPlayService.class);
        intentClick.setAction(MusicPlayService.ACTION_PLAY);
        intentClick.putExtra(MusicPlayService.POSITION, position);
        startService(intentClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlayMusic:
                if (isSing) {
                    isSing = false;
                    mBtnPlay.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                    mIntent.setAction(MusicPlayService.ACTION_PAUSE);
                    startService(mIntent);
                } else {
                    isSing = true;
                    mBtnPlay.setBackground(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                    mIntent.setAction(MusicPlayService.ACTION_RESUME);
                    startService(mIntent);
                }
                break;
            case R.id.btnNextMusic:
                mIntent.setAction(MusicPlayService.ACTION_NEXT);
                startService(mIntent);
                break;
            case R.id.btnPreviousMusic:
                mIntent.setAction(MusicPlayService.ACTION_PREVIOUS);
                startService(mIntent);
                break;
        }
    }

    /**
     * Send check Run
     */
    private void sendCheckRun() {
        mIntent = new Intent(getApplicationContext(), MusicPlayService.class);
        mIntent.setAction(CHECK_RUN);
        startService(mIntent);
    }
}
