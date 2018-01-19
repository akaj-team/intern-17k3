package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;
import vn.asiantech.internship.service.MusicService;

/**
 * Created by anh.quach on 1/17/18.
 * Play Control Fragment.
 */
public class PlayControlFragment extends Fragment {
    private CircleImageView mCircleImageViewAvatarMusic;
    private TextView mTvTittle;
    private TextView mTvArtist;
    private ImageView mImgPlay;
    private ImageView mImgNext;
    private ImageView mImgPrevious;
    private Intent mIntent;
    private boolean mIsPlay = false;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case "sendData":
                        Music music = intent.getParcelableExtra("music");
                        if (music != null) {
                            mTvTittle.setText(music.getTittle());
                            mTvArtist.setText(music.getSinger());
                            mCircleImageViewAvatarMusic.setImageResource(music.getAvatar());
                        }
                }
            }
        }
    };

    public static PlayControlFragment newInstance() {
        return new PlayControlFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playback_control_bottom, container, false);
        initViews(view);
        registerBroadcast();
        mIntent = new Intent();
        mIntent = new Intent(getContext(), MusicService.class);
        mImgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsPlay) {
                    mImgPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    mIntent.setAction("pause");
                } else {
                    mImgPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                    mIntent.setAction("play");
                }
                getContext().startService(mIntent);
                mIsPlay = !mIsPlay;
            }
        });
        mImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.setAction("next");
                getContext().startService(mIntent);
            }
        });
        mImgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.setAction("previous");
                getContext().startService(mIntent);
            }
        });
        return view;
    }

    private void initViews(View view) {
        mTvTittle = view.findViewById(R.id.tvTitle);
        mTvArtist = view.findViewById(R.id.tvArtist);
        mImgPlay = view.findViewById(R.id.imgPlay);
        mImgNext = view.findViewById(R.id.imgNext);
        mImgPrevious = view.findViewById(R.id.imgPrevious);
        mCircleImageViewAvatarMusic = view.findViewById(R.id.circleimageviewAvatarPlayControl);
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.action_send_data));
        getContext().registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(mBroadcastReceiver);
    }
}
