package vn.asiantech.internship.ui.viewpager.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Song;

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener, View.OnClickListener {
    private static final String POSITION = "position";
    private static final String ACTION_PLAY = "Play";
    private RecyclerView mRecyclerViewSong;
    private List<Song> mSongs;
    private Intent mIntentSendAction;
    private boolean sing;
    private List<Integer> mListSong;
    private TextView mTvTimeSong;
    private TextView mTvNameSong;
    private SeekBar mSeekBarSong;
    private LinearLayout mLlController;
    private Button mBtnPlayPause;
    private ImageView mImgAvatarSong;
    private boolean mRunning;
    private SharedPreferences mSharedPreferences;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "CheckPlay")) {
                int currentTime = intent.getIntExtra("CurrentTime", 0);
                int totalTime = intent.getIntExtra("TotalTime", 0);
                mTvTimeSong.setText(convertTime(currentTime).concat("/").concat(convertTime(totalTime)));
                mSeekBarSong.setProgress(currentTime);
                mSeekBarSong.setMax(totalTime);
                mSeekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //No-op
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //No-op
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        int time = seekBar.getProgress();
                        mIntentSendAction.setAction("Move");
                        mIntentSendAction.putExtra("Move", time);
                        startService(mIntentSendAction);
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initViews();
        initData();
        initAdapter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CheckPlay");
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void updateController(boolean checkRun) {
        if (checkRun) {
            mLlController.setVisibility(View.VISIBLE);
        } else {
            mLlController.setVisibility(View.INVISIBLE);
        }
    }

    private void initViews() {
        mRecyclerViewSong = findViewById(R.id.recycleViewSong);
        mLlController = findViewById(R.id.llController);
        mBtnPlayPause = findViewById(R.id.btnPlayPause);
        mIntentSendAction = new Intent(this, ServiceMusic.class);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);
        mTvTimeSong = findViewById(R.id.tvTimeSong);
        mTvNameSong = findViewById(R.id.tvNameSong);
        mTvNameSong.setSelected(true);
        mSeekBarSong = findViewById(R.id.seekBarSong);
        mImgAvatarSong = findViewById(R.id.imgAvatarSong);
        btnStop.setOnClickListener(this);
        mBtnPlayPause.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        mSharedPreferences = getSharedPreferences("Music", MODE_PRIVATE);
        mRunning = mSharedPreferences.getBoolean("Run", false);
        updateController(mRunning);
        setBtnPlayPause(mRunning);
    }

    private void setBtnPlayPause(boolean checkSing) {
        if (checkSing) {
            mBtnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause_white_48dp));
        } else {
            mBtnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play_arrow_white_48dp));
        }
    }

    private void initData() {
        mListSong = new ArrayList<>();
        mSongs = new ArrayList<>();
        mSongs.add(new Song("Anh đang nơi đâu Anh đang nơi đâu ", R.drawable.ic_plate, R.raw.anhdangnoidau));
        mSongs.add(new Song("Buồn của anh Buồn của anh Buồn của anh", R.drawable.ic_plate, R.raw.buoncuaanh));
        mSongs.add(new Song("Đã lỡ yêu em nhiều", R.drawable.ic_plate, R.raw.daloyeuemnhieu));
        mSongs.add(new Song("Kém duyên", R.drawable.ic_plate, R.raw.kemduyen));
        mSongs.add(new Song("Người lạ ơi", R.drawable.ic_plate, R.raw.nguoilaoi));
        mSongs.add(new Song("Buồn của anh", R.drawable.ic_plate, R.raw.buoncuaanh));
        mSongs.add(new Song("Đã lỡ yêu 1 em nhiều", R.drawable.ic_plate, R.raw.daloyeuemnhieu));
        mSongs.add(new Song("Kém 1 duyên", R.drawable.ic_plate, R.raw.kemduyen));
        mSongs.add(new Song("Người 1 lạ ơi", R.drawable.ic_plate, R.raw.nguoilaoi));
        mSongs.add(new Song("Buồn 1 của anh", R.drawable.ic_plate, R.raw.buoncuaanh));
        mSongs.add(new Song("Đã lỡ yêu 2 em nhiều", R.drawable.ic_plate, R.raw.daloyeuemnhieu));
        mSongs.add(new Song("Kém 2 duyên", R.drawable.ic_plate, R.raw.kemduyen));
        mSongs.add(new Song("Người lạ 2 ơi", R.drawable.ic_plate, R.raw.nguoilaoi));
        mSongs.add(new Song("Buồn của 2 anh", R.drawable.ic_plate, R.raw.buoncuaanh));
        mSongs.add(new Song("Đã lỡ yêu ", R.drawable.ic_plate, R.raw.daloyeuemnhieu));
        mSongs.add(new Song("Kém 3", R.drawable.ic_plate, R.raw.kemduyen));
        mSongs.add(new Song("Người ơi 3", R.drawable.ic_plate, R.raw.nguoilaoi));
        mSongs.add(new Song("Buồn của anh 3", R.drawable.ic_plate, R.raw.buoncuaanh));
    }

    private void initAdapter() {
        MusicAdapter musicAdapter = new MusicAdapter(mSongs, this);
        mRecyclerViewSong.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewSong.setAdapter(musicAdapter);
    }

    @Override
    public void onClickItem(int position) {
        mTvNameSong.setText(mSongs.get(position).getName());
        mImgAvatarSong.setImageResource(mSongs.get(position).getAvatar());
        mListSong.add(position);
        mIntentSendAction.setAction(ACTION_PLAY);
        mIntentSendAction.putExtra(POSITION, mSongs.get(position).getResource());
        startService(mIntentSendAction);
        mRunning = true;
        updateController(true);
        setBtnPlayPause(mRunning);
        SharedPreferences.Editor editorEdit = mSharedPreferences.edit();
        editorEdit.putBoolean("Run", true);
        editorEdit.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStop:
                mIntentSendAction.setAction("Stop");
                startService(mIntentSendAction);
                mRunning = false;
                updateController(false);
                setBtnPlayPause(mRunning);
                SharedPreferences.Editor editorEdit = mSharedPreferences.edit();
                editorEdit.putBoolean("Run", false);
                editorEdit.apply();
                break;
            case R.id.btnPlayPause:
                if (sing) {
                    sing = false;
                    mIntentSendAction.setAction("Pause");
                } else {
                    sing = true;
                    mIntentSendAction.setAction("Resume");
                }
                startService(mIntentSendAction);
                setBtnPlayPause(sing);
                break;
            case R.id.btnPrevious:
                if (mListSong.size() >= 2) {
                    mListSong.remove(mListSong.size() - 1);
                    int songPrevious = mListSong.get(mListSong.size() - 1);
                    mTvNameSong.setText(mSongs.get(songPrevious).getName());
                    mImgAvatarSong.setImageResource(mSongs.get(songPrevious).getAvatar());
                    mIntentSendAction.setAction(ACTION_PLAY);
                    mIntentSendAction.putExtra(POSITION, mSongs.get(songPrevious).getResource());
                    startService(mIntentSendAction);
                    sing = true;
                    setBtnPlayPause(true);
                } else {
                    mIntentSendAction.setAction(ACTION_PLAY);
                    mIntentSendAction.putExtra(POSITION, mSongs.get(0).getResource());
                    startService(mIntentSendAction);
                }
                break;
            case R.id.btnNext:
                Random r = new Random();
                int songNext = r.nextInt(mSongs.size());
                mTvNameSong.setText(mSongs.get(songNext).getName());
                mImgAvatarSong.setImageResource(mSongs.get(songNext).getAvatar());
                mListSong.add(songNext);
                mIntentSendAction.setAction(ACTION_PLAY);
                mIntentSendAction.putExtra(POSITION, mSongs.get(songNext).getResource());
                startService(mIntentSendAction);
                sing = true;
                setBtnPlayPause(true);
        }
    }

    private String convertTime(int millisSecond) {
        int minute = millisSecond / 60000;
        int second = (millisSecond / 1000) % 60;
        return minute + ":" + second;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
