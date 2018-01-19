package vn.asiantech.internship.ui.viewpager_tablayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;
import vn.asiantech.internship.service.MusicService;
import vn.asiantech.internship.ui.viewpager_tablayout.adapter.ListMusicsAdapter;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.PlayControlFragment;

/**
 * Created by anh.quach on 1/17/18.
 * Play Mucsic Activity
 */
public class PlayMusicActivity extends AppCompatActivity implements ListMusicsAdapter.OnClickItemListener {
    private RecyclerView mRecyclerViewMusic;
    private FrameLayout mFrPlayControl;
    private Intent mIntent;
    private ArrayList<Music> mMusicLists = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        mIntent = new Intent(this, MusicService.class);
        initView();
        initData();
        initAdapter();
        mFrPlayControl.setVisibility(View.GONE);
    }

    private void initAdapter() {
        ListMusicsAdapter mAdapter = new ListMusicsAdapter(mMusicLists, this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mAdapter);
    }

    private void initData() {
        mMusicLists.add(new Music("01", "Tell me you love me", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("02", "Blue", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("03", "Some", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("04", "Galaxy", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("05", "Fix me", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("06", "Youth", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("07", "Closer", "The Chainsmoker", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("08", "Paris", "The Chainsmoker", R.drawable.img_avatar, R.raw.music1));
        mIntent.setAction("data");
        mIntent.putParcelableArrayListExtra("listData", mMusicLists);
        startService(mIntent);
    }

    private void initView() {
        mRecyclerViewMusic = findViewById(R.id.recyclerViewListMusics);
        mFrPlayControl = findViewById(R.id.fr_playback_controls);
    }

    protected void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_playback_controls, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickItem(int pos) {
        mFrPlayControl.setVisibility(View.VISIBLE);
        showFragment(PlayControlFragment.newInstance());
        mIntent.setAction("start");
        mIntent.putExtra("position", pos);
        startService(mIntent);
    }
}
