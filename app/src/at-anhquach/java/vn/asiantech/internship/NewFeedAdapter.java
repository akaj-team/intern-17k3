package vn.asiantech.internship;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.model.NewFeed;

public class NewFeedAdapter extends RecyclerView.Adapter<NewFeedAdapter.NewFeedViewHolder> {
    private List<NewFeed> mNewFeedList;
    private OnItemClickListener mOnItemClickListener;

    NewFeedAdapter(List<NewFeed> newFeedList, OnItemClickListener onItemClickListener) {
        mNewFeedList = newFeedList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public NewFeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_newfeed, viewGroup, false);
        return new NewFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewFeedAdapter.NewFeedViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mNewFeedList.size();
    }

    class NewFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvName;
        private TextView mTvStatus;
        private TextView mTvSumReact;
        private ImageView mImgLike;
        private ImageView mImgDisLike;

        public NewFeedViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mTvSumReact = itemView.findViewById(R.id.tvSumReact);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDisLike = itemView.findViewById(R.id.imgDisLike);
            mImgLike.setOnClickListener(this);
            mImgDisLike.setOnClickListener(this);
        }

        private void onBindData() {
            NewFeed newFeed = mNewFeedList.get(getAdapterPosition());
            mTvName.setText(newFeed.getName());
            mTvStatus.setText(newFeed.getStatus());
            mTvSumReact.setText(String.valueOf(newFeed.getSumReact()));
            setColorButtonReact(newFeed);
        }

        private void setColorButtonReact(NewFeed newFeed) {
            if (newFeed.getSumReact() == 0) {
                mImgLike.setSelected(false);
                mImgDisLike.setSelected(false);
                mTvSumReact.setTextColor(Color.BLACK);
            } else {
                if (newFeed.getSumReact() > 0) {
                    mImgLike.setSelected(true);
                    mImgDisLike.setSelected(false);
                    mTvSumReact.setTextColor(Color.GREEN);
                } else {
                    mImgDisLike.setSelected(true);
                    mImgLike.setSelected(false);
                    mTvSumReact.setTextColor(Color.RED);
                }
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgLike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickLike(getAdapterPosition());
                    }
                    break;
                case R.id.imgDisLike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickDisLike(getAdapterPosition());
                    }
                    break;
            }
        }
    }

    protected interface OnItemClickListener {
        void onClickLike(int position);

        void onClickDisLike(int position);
    }
}
