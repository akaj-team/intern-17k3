package vn.asiantech.internship.models;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import vn.asiantech.internship.R;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    private List<Status> mStatusList;
    private OnItemClickListener mOnItemClickListener;

    public StatusAdapter(List<Status> statusList, OnItemClickListener onItemClickListener) {
        mStatusList = statusList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public StatusAdapter.StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
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
        private TextView mTvName;
        private TextView mTvStatus;
        private TextView mTvCount;
        private ImageView mImgLike;
        private ImageView mImgDislike;

        StatusViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvStatus = itemView.findViewById(R.id.tvDescription);
            mTvCount = itemView.findViewById(R.id.tvCount);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDislike = itemView.findViewById(R.id.imgDislike);
            mImgLike.setOnClickListener(this);
            mImgDislike.setOnClickListener(this);
        }

        private void onBindData() {
            Status status = mStatusList.get(getAdapterPosition());
            mTvName.setText(status.getName());
            mTvStatus.setText(status.getDescription());
            mTvCount.setText(String.valueOf(status.getCount()));
            checklikeStatus(status.getCount());
        }

        private void checklikeStatus(int countLike) {
            if (countLike == 0) {
                mImgLike.setSelected(false);
                mImgDislike.setSelected(false);
                mTvCount.setTextColor(Color.GRAY);
            } else if (countLike < 0) {
                mImgLike.setSelected(false);
                mImgDislike.setSelected(true);
                mTvCount.setTextColor(Color.RED);
            } else {
                mImgLike.setSelected(true);
                mImgDislike.setSelected(false);
                mTvCount.setTextColor(Color.GREEN);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgLike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onLikeClick(getAdapterPosition());
                        break;
                    }
                case R.id.imgDislike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onDisLikeClick(getAdapterPosition());
                        break;
                    }
            }
        }
    }

    public interface OnItemClickListener {

        void onLikeClick(int position);

        void onDisLikeClick(int position);
    }
}
