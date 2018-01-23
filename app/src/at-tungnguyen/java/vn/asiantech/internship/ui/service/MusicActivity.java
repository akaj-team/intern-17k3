package vn.asiantech.internship.ui.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener, View.OnClickListener {
    private ArrayList<Music> mMusicList;
    private RecyclerView mRecyclerViewMusic;
    private LinearLayout mLlMusic;
    private Button mBtnPlay;
    private Button mBtnNextMusic;
    private Button mBtnPrevious;
    private TextView mTvNameMusic;
    private TextView mTvSingle;
    boolean isSelected = true;
    private static MusicPlayService mMusicPlayService;
    private boolean isPlay = false;
    private int mPosition = 0;
    private Intent mIntent;
    static boolean isBound = false;

    /**
     * ServiceConnection used to connect to service
     */
    public ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPlayService.MusicBinder binder = (MusicPlayService.MusicBinder) service;
            //get service
            mMusicPlayService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //No-opp
            isBound = false;
            mMusicPlayService = null;
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
        bindService(mIntent, musicConnection, Context.BIND_AUTO_CREATE);
    }

    private void initViews() {
        mBtnPrevious = findViewById(R.id.btnPreviousMusic);
        mBtnNextMusic = findViewById(R.id.btnNextMusic);
        mLlMusic = findViewById(R.id.llToolMusic);
        mRecyclerViewMusic = findViewById(R.id.recyclerviewMusic);
        mBtnPlay = findViewById(R.id.btnPlayMusic);
        mTvNameMusic = findViewById(R.id.tvNameMusic);
        mTvSingle = findViewById(R.id.tvSingle);
    }

    private void initAdapter() {
        mMusicList = new ArrayList<>();
        MusicAdapter mMusicAdapter = new MusicAdapter(mMusicList, this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mMusicAdapter);
    }

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

    private void initListener() {
        mBtnPrevious.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
        mBtnNextMusic.setOnClickListener(this);
    }

    @Override
    public void onItemClick(Music music, int position) {
        if (isSelected) {
            mLlMusic.setVisibility(View.VISIBLE);
        } else {
            mLlMusic.setVisibility(View.INVISIBLE);
        }
        mTvNameMusic.setText(mMusicPlayService.getNameMusic(position));
        mTvSingle.setText(mMusicPlayService.getSingle(position));
        mMusicPlayService.onItemMusicClick(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlayMusic:
                if (!isPlay) {
                    isPlay = true;
                    mIntent.setAction("play");
                    startService(mIntent);
                    mBtnPlay.setSelected(false);
                    isPlay = true;
                } else {
                    mIntent.setAction("pause");
                    startService(mIntent);
                    mBtnPlay.setSelected(true);
                    isPlay = false;
                }
                break;
            case R.id.btnNextMusic:
                mPosition++;
                mMusicPlayService.onNext(mPosition);
                if (mPosition < mMusicList.size()) {
                    mTvNameMusic.setText(mMusicPlayService.getNameMusic(mPosition));
                    mTvSingle.setText(mMusicPlayService.getSingle(mPosition));
                } else if (mPosition > mMusicList.size() - 1) {
                    Toast.makeText(this, R.string.tv_end, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPreviousMusic:
                mPosition--;
                mMusicPlayService.onPrevious(mPosition);
                if (mPosition < mMusicList.size()) {
                    mTvNameMusic.setText(mMusicPlayService.getNameMusic(mPosition));
                    mTvSingle.setText(mMusicPlayService.getSingle(mPosition));
                } else if (mPosition == mMusicList.size() - 4) {
                }
        }
    }
}
