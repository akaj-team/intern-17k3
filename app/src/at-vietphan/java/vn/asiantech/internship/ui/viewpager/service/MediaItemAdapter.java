package vn.asiantech.internship.ui.viewpager.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.service.util.UtilFunctions;

/**
 * Created by vietphan on 17/01/2018.
 * Class SongAdapter
 */
public class MediaItemAdapter extends RecyclerView.Adapter<MediaItemAdapter.SongViewHolder> {
    private List<MediaItem> mMediaItems;
    private OnItemClickListener mOnItemClickListener;

    MediaItemAdapter(List<MediaItem> mediaItems, OnItemClickListener onItemClickListener) {
        this.mMediaItems = mediaItems;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.onBindData(mMediaItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mMediaItems.size();
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

        private void onBindData(MediaItem mediaItem) {
            if (mediaItem != null) {
                mTvSongName.setText(mediaItem.getTitle());
                mTvArtist.setText(mediaItem.getArtist());
                mTvDuration.setText(UtilFunctions.getDuration(mediaItem.getDuration()));
            }
        }

        private void initListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
