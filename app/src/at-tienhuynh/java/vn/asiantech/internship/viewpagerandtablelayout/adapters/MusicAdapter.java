package vn.asiantech.internship.viewpagerandtablelayout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.models.Music;

/**
 * Created by TienHuynh on 17/01/2018.
 * AsianTech Co., Ltd
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicAdapterViewHolder> {

    private List<Music> mMusicLists;
    private MusicAdapter.onItemClick mOnItemClick;

    public MusicAdapter(List<Music> musicLists, onItemClick onItemClick) {
        mMusicLists = musicLists;
        mOnItemClick = onItemClick;
    }

    @Override
    public MusicAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play_music, parent, false);
        final MusicAdapterViewHolder musicAdapterViewHolder = new MusicAdapterViewHolder(view);
        musicAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClick.onItemClickListener(musicAdapterViewHolder.getAdapterPosition());
            }
        });
        return musicAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(MusicAdapterViewHolder holder, int position) {
        holder.onBindData(mMusicLists);
    }

    @Override
    public int getItemCount() {
        return mMusicLists.size();
    }

    class MusicAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView mTvNameSong;
        TextView mTvSinger;
        TextView mTvTime;

        MusicAdapterViewHolder(View itemView) {
            super(itemView);
            mTvNameSong = itemView.findViewById(R.id.tvNameSong);
            mTvSinger = itemView.findViewById(R.id.tvSingerSong);
            mTvTime = itemView.findViewById(R.id.tvTimeItem);
        }

        private void onBindData(List<Music> musicLists) {
            Music music = musicLists.get(getAdapterPosition());
            if (music != null) {
                mTvNameSong.setText(music.getName());
                mTvSinger.setText(music.getSinger());
                mTvTime.setText(music.getTime());
            }
        }
    }

    /**
     * On Item RecyclerView Click
     */
    public interface onItemClick {
        void onItemClickListener(int potion);
    }
}
