package vn.asiantech.internship.ui.viewpager_tablayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Music;

/**
 * Created by anh.quach on 1/17/18.
 * Adapter list musics.
 */

public class ListMusicsAdapter extends RecyclerView.Adapter<ListMusicsAdapter.ListMusicsViewHolder> {
    private List<Music> mListMusics;
    private OnClickItemListener mOnItemClickListener;

    @Override
    public ListMusicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new ListMusicsViewHolder(view);
    }

    public ListMusicsAdapter(List<Music> newFeedList, OnClickItemListener onClickItemListener) {
        mListMusics = newFeedList;
        mOnItemClickListener = onClickItemListener;
    }

    @Override
    public void onBindViewHolder(ListMusicsViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mListMusics.size();
    }

    class ListMusicsViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNameMusic;
        private TextView mTvSinger;
        private TextView mTvIdMusic;
        private ImageView mImgAvatarMusic;

        ListMusicsViewHolder(View itemView) {
            super(itemView);
            mTvNameMusic = itemView.findViewById(R.id.tvNameMusic);
            mTvSinger = itemView.findViewById(R.id.tvSinger);
            mImgAvatarMusic = itemView.findViewById(R.id.imgAvatarMusic);
            mTvIdMusic = itemView.findViewById(R.id.tvIdMusic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

        private void onBindData() {
            Music music = mListMusics.get(getAdapterPosition());
            mTvNameMusic.setText(music.getTittle());
            mTvSinger.setText(music.getSinger());
            mTvIdMusic.setText(music.getId());
            mImgAvatarMusic.setImageResource(music.getAvatar());
        }

    }

    public interface OnClickItemListener {
        public void onClickItem(int pos);
    }
}
