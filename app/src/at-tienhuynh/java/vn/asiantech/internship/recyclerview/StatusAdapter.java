package vn.asiantech.internship.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.recyclerview.models.Status;

/**
 * Created at 2017
 * Created by jackty on 29/11/2017.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {

    private List<Status> mStatusList;
    private OnItemClickListener mOnItemClickListener;

    public StatusAdapter(List<Status> statusList, OnItemClickListener onItemClickListener) {
        mStatusList = statusList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public StatusAdapter.StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.StatusViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    class StatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvTitle;
        private TextView mTvDescription;
        private TextView mTvCountLike;
        private ImageView mImgLike;
        private ImageView mImgDisLike;

        StatusViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
            mTvDescription = itemView.findViewById(R.id.tvDescription);
            mTvCountLike = itemView.findViewById(R.id.tvNumCountLike);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDisLike = itemView.findViewById(R.id.imgDislike);
            mImgLike.setOnClickListener(this);
            mImgDisLike.setOnClickListener(this);
        }

        private void onBindData() {
            Status status = mStatusList.get(getAdapterPosition());
            mTvTitle.setText(status.getTitle());
            mTvDescription.setText(status.getDescription());
            mTvCountLike.setText(String.valueOf(status.getNumlike()));
            if (status.getNumlike() > 0) {
                mImgLike.setSelected(true);
                mImgDisLike.setSelected(false);
                mTvCountLike.setTextColor(Color.GREEN);
            } else if (status.getNumlike() < 0) {
                mImgLike.setSelected(false);
                mImgDisLike.setSelected(true);
                mTvCountLike.setTextColor(Color.RED);
            } else {
                mImgLike.setSelected(false);
                mImgDisLike.setSelected(false);
                mTvCountLike.setTextColor(Color.GRAY);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgLike:
                    mOnItemClickListener.onLikeClick(getAdapterPosition());
                    break;
                case R.id.imgDislike:
                    mOnItemClickListener.onDisLikeClick(getAdapterPosition());
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onLikeClick(int position);

        void onDisLikeClick(int position);
    }
}
