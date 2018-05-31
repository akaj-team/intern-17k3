package vn.asiantech.internship.ui.viewpager.service.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.models.Song;
import vn.asiantech.internship.ui.viewpager.service.util.Constants;
import vn.asiantech.internship.ui.viewpager.service.util.FunctionsUtil;

/**
 * Created by vietphan on 17/01/2018.
 * Class SongAdapter
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> mSongs;
    private OnItemClickListener mOnItemClickListener;

    public SongAdapter(List<Song> songs, OnItemClickListener onItemClickListener) {
        this.mSongs = songs;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.onBindData(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * Class SongViewHolder
     */
    class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvSongName;
        private TextView mTvArtist;
        private TextView mTvDuration;

        SongViewHolder(View view) {
            super(view);
            initViews();
            initListener();
        }

        private void initViews() {
            mTvSongName = itemView.findViewById(R.id.tvSongName);
            mTvArtist = itemView.findViewById(R.id.tvArtist);
            mTvDuration = itemView.findViewById(R.id.tvDuration);
        }

        private void onBindData(Song song) {
            if (song != null) {
                mTvSongName.setText(song.getTitle());
                mTvArtist.setText(song.getArtist());
                mTvDuration.setText(FunctionsUtil.getDuration(song.getDuration()));
            }
        }

        private void initListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constants.mIsSongPaused = false;
                    Constants.mSongPosition = getAdapterPosition();
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
