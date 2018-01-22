package vn.asiantech.internship.ui.viewpager.music;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Song;

/**
 * Created by hoangnhat on 17/01/2018.
 * Class adapter
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.SongViewHolder> {
    private List<Song> mSongs;
    private OnItemClickListener mOnItemClickListener;

    MusicAdapter(List<Song> song, OnItemClickListener onItemClickListener) {
        this.mSongs = song;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNameSong;
        private ImageView mImgAvatarSong;

        SongViewHolder(View itemView) {
            super(itemView);
            mTvNameSong = itemView.findViewById(R.id.tvNameSong);
            mImgAvatarSong = itemView.findViewById(R.id.imgAvatarSong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                }
            });
        }

        public void onBindData() {
            Song song = mSongs.get(getAdapterPosition());
            mTvNameSong.setText(song.getName());
            mImgAvatarSong.setImageResource(song.getAvatar());
        }
    }
}
