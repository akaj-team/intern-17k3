package vn.asiantech.internship.ui.service;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener {
    private List<Music> mMusicList;
    private RecyclerView mRecyclerViewMusic;
    private MusicAdapter mMusicAdapter;
    private LinearLayout mLlMusic;
    private Button mBtnPlay;
    private TextView mTvNameMusic;
    private TextView mTvSingle;
    private boolean isSelected = true;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initViews();
        initAdapter();
        initData();
    }

    private void initViews() {
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
        mRecyclerViewMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        mMusicList.add(new Music("Người lạ ơi", "Karrik", false));
        mMusicList.add(new Music("Tình Đơn Phương", "Lam Trường", false));
    }

    @Override
    public void onItemClick(Music music,int position) {
        mLlMusic.setVisibility(View.VISIBLE);
        mTvNameMusic.setText(music.getNameMusic());
        mTvSingle.setText(music.getSingle());
        Intent intent = new Intent(getApplicationContext(), MusicPlayService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("music", music);
        intent.putExtra("data", bundle);
        intent.setAction("data");
        startService(intent);
//        getClickPlay();
    }

    private void getClickPlay() {
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnPlay.setSelected(true);
                Intent intent = new Intent(MusicActivity.this, MusicPlayService.class);
                startService(intent);
            }
        });
    }
}
