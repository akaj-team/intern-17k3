package vn.asiantech.internship.ui.viewpager_tablayout.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Intent mIntentService;
    private Intent mIntentNotif = new Intent();
    private boolean isPlaying;
    private ArrayList<Music> mMusicLists = new ArrayList<>();
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initView();
        initData();
        sendListMusicsToService();
        initAdapter();
        initSharePreference();
        isPlaying = mPreferences.getBoolean(getString(R.string.key_is_playing), false);
        showFragment(PlayControlFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlaying) {
            mFrPlayControl.setVisibility(View.VISIBLE);
            int pos = mPreferences.getInt(getString(R.string.key_position), 0);
            sendDataBroadcast(pos);
        } else
            mFrPlayControl.setVisibility(View.GONE);
    }

    private void initAdapter() {
        ListMusicsAdapter mAdapter = new ListMusicsAdapter(mMusicLists, this);
        mRecyclerViewMusic.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewMusic.setAdapter(mAdapter);
    }

    private void initData() {
        mMusicLists.add(new Music("01", "Tell me you love me", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music1));
        mMusicLists.add(new Music("02", "Blue", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music2));
        mMusicLists.add(new Music("03", "Some", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music3));
        mMusicLists.add(new Music("04", "Galaxy", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music4));
        mMusicLists.add(new Music("05", "Fix me", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music5));
        mMusicLists.add(new Music("06", "Youth", "Bolbbalgan4", R.drawable.img_avatar, R.raw.music6));
        mMusicLists.add(new Music("07", "Closer", "The Chainsmoker", R.drawable.img_avatar, R.raw.music7));
        mMusicLists.add(new Music("08", "Paris", "The Chainsmoker", R.drawable.img_avatar, R.raw.music8));
    }

    private void sendListMusicsToService() {
        mIntentService = new Intent(this, MusicService.class);
        mIntentService.setAction(getString(R.string.action_send_data));
        mIntentService.putParcelableArrayListExtra(getString(R.string.key_list_data), mMusicLists);
        startService(mIntentService);
    }

    private void initSharePreference() {
        mPreferences = getSharedPreferences(getResources().getString(R.string.key_save_data), Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
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
        mEditor.putBoolean(getString(R.string.key_is_playing), true);
        mEditor.apply();
        mFrPlayControl.setVisibility(View.VISIBLE);
        mIntentService.setAction(getString(R.string.action_start));
        mIntentService.putExtra(getString(R.string.key_position), pos);
        startService(mIntentService);
    }

    private void sendDataBroadcast(int position) {
        mIntentNotif.setAction(getString(R.string.action_send_data));
        mIntentNotif.putExtra(getString(R.string.key_music), mMusicLists.get(position));
        sendBroadcast(mIntentNotif);
    }
}
