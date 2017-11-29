package vn.asiantech.internship;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.models.Status;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    private List<Status> mStatusList;
    private OnItemClickListener mOnItemClickListener;

    StatusAdapter(List<Status> statusList, OnItemClickListener onItemClickListener) {
        mStatusList = statusList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    public interface OnItemClickListener {
        void onLikeClick(int position);

        void onDislikeClick(int position);
    }

    class StatusViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvStatus;
        private TextView mTvContent;
        private TextView mTvCountLike;
        private Button mBtnLike;
        private Button mBtnDislike;

        StatusViewHolder(View itemView) {
            super(itemView);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mTvContent = itemView.findViewById(R.id.tvContent);
            mTvCountLike = itemView.findViewById(R.id.tvCountLike);
            mBtnLike = itemView.findViewById(R.id.btnLike);
            mBtnDislike = itemView.findViewById(R.id.btnDislike);
            mBtnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onLikeClick(getAdapterPosition());
                        checkLikeForStatus();
                    }
                }
            });
            mBtnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onDislikeClick(getAdapterPosition());
                        checkLikeForStatus();
                    }
                }
            });
        }

        private void onBindData() {
            Status status = mStatusList.get(getAdapterPosition());
            mTvStatus.setText(status.getName());
            mTvContent.setText(status.getContent());
            String countLike = String.valueOf(status.getCountLike());
            mTvCountLike.setText(countLike);
            checkLikeForStatus();
        }

        private void checkLikeForStatus() {
            int countLike = Integer.parseInt(mTvCountLike.getText().toString().trim());
            if (countLike == 0) {
                mBtnLike.setPressed(false);
                mBtnDislike.setPressed(false);
                mTvCountLike.setTextColor(Color.GRAY);
            } else if (countLike < 0) {
                mBtnLike.setPressed(false);
                mBtnDislike.setPressed(true);
                mTvCountLike.setTextColor(Color.RED);
            } else {
                mBtnLike.setPressed(true);
                mBtnDislike.setPressed(false);
                mTvCountLike.setTextColor(Color.GREEN);
            }
        }
    }
}
