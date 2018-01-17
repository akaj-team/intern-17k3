package vn.asiantech.internship.ui.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 17/01/2018.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Music> mMusicLists;
    private OnItemClickListener mOnItemClickListener;

    public MusicAdapter(List<Music> mMusicLists, OnItemClickListener mOnItemClickListener) {
        this.mMusicLists = mMusicLists;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicAdapter.MusicViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mMusicLists.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNameMusic;
        private TextView mTvSingle;

        MusicViewHolder(View itemView) {
            super(itemView);
            mTvNameMusic = itemView.findViewById(R.id.tvNameMusic);
            mTvSingle = itemView.findViewById(R.id.tvSingle);
        }

        private void onBindData() {
            final Music music = mMusicLists.get(getAdapterPosition());
            mTvNameMusic.setText(music.getNameMusic());
            mTvSingle.setText(music.getSingle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(music);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Music music);
    }
}
