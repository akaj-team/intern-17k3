package vn.asiantech.internship.ui.service;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

public class MusicActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener{
    private List<Music> mMusicList;
    private RecyclerView mRecyclerViewMusic;
    private MusicAdapter mMusicAdapter;
    private LinearLayout mLlMusic;
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
    }
    private void initAdapter() {
        mMusicList = new ArrayList<>();
        mMusicAdapter = new MusicAdapter(mMusicList,this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mMusicAdapter);
        mRecyclerViewMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void initData() {
        mMusicList.add(new Music("Nguoi la oi", "Karrik"));
        mMusicList.add(new Music("Tinh don phuong", "Lam Truong"));
        mMusicList.add(new Music("Tinh don phuong", "Lam Truong"));
        mMusicList.add(new Music("Tinh don phuong", "Lam Truong"));
        mMusicList.add(new Music("Tinh don phuong", "Lam Truong"));
    }

    @Override
    public void onItemClick(Music music) {
        mLlMusic.setVisibility(View.VISIBLE);
    }
}
