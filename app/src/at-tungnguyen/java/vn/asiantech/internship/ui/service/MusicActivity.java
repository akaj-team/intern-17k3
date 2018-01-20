package vn.asiantech.internship.ui.service;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener, View.OnClickListener {
    private List<Music> mMusicList;
    private RecyclerView mRecyclerViewMusic;
    private MusicAdapter mMusicAdapter;
    private LinearLayout mLlMusic;
    private Button mBtnPlay;
    private Button mBtnNextMusic;
    private Button mBtnPrevious;
    private TextView mTvNameMusic;
    private TextView mTvSingle;
    boolean isSelected = true;
    private MusicPlayService mMusicPlayService;
    private boolean isPlay = false;
    private int mPosition = 0;
    private Intent mIntent;

    /**
     * ServiceConnection used to connect to service
     */
    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPlayService.MusicBinder binder = (MusicPlayService.MusicBinder) service;
            //get service
            mMusicPlayService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //No-opp
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initViews();
        initAdapter();
        initData();
        initListener();
        mIntent = new Intent(getApplicationContext(), MusicPlayService.class);
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
        mMusicAdapter = new MusicAdapter(mMusicList, this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mMusicAdapter);
    }

    private void initData() {
        mMusicList.add(new Music("Người lạ ơi", "Karrik", false));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", false));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", false));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", false));
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
        mTvNameMusic.setText(music.getNameMusic());
        mTvSingle.setText(music.getSingle());
        mMusicPlayService.onItemMusicClick(position);
        Notification();
//        startService(mIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlayMusic:
                if (!isPlay) {
                    isPlay = true;
                    mBtnPlay.setSelected(true);
                    mMusicPlayService.onPause();
                } else {
                    isPlay = false;
                    mBtnPlay.setSelected(false);
                    mMusicPlayService.onPlay();
                }
                break;
            case R.id.btnNextMusic:
                mPosition++;
//                mTvNameMusic.setText(mMusicPlayService.getNameMusic(music));
//                Log.d("", "onClick: "+mMusicPlayService.getNameMusic(music));
                mMusicPlayService.onNext();
                break;
            case R.id.btnPreviousMusic:
                mMusicPlayService.onPrevious();
        }
    }

    public void Notification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_input_black_24dp);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText("Hello World!");
        mNotificationManager.notify(1, mBuilder.build());

    }
}
