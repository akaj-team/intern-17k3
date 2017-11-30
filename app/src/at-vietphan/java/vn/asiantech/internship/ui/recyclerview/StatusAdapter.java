package vn.asiantech.internship.ui.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
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

    class StatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvStatus;
        private TextView mTvContent;
        private TextView mTvCountLike;
        private Button mBtnLike;
        private Button mBtnDislike;

        StatusViewHolder(View itemView) {
            super(itemView);
            initViews();
            initListener();
        }

        private void initViews() {
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mTvContent = itemView.findViewById(R.id.tvContent);
            mTvCountLike = itemView.findViewById(R.id.tvCountLike);
            mBtnLike = itemView.findViewById(R.id.btnLike);
            mBtnDislike = itemView.findViewById(R.id.btnDislike);
        }

        private void initListener() {
            mBtnLike.setOnClickListener(this);
            mBtnDislike.setOnClickListener(this);
        }

        private void onBindData() {
            Status status = mStatusList.get(getAdapterPosition());
            mTvStatus.setText(status.getName());
            mTvContent.setText(status.getContent());
            mTvCountLike.setText(String.valueOf(status.getCountLike()));
            checkLikeForStatus(status.getCountLike());
        }

        private void checkLikeForStatus(int countLike) {
            if (countLike == 0) {
                mBtnLike.setSelected(false);
                mBtnDislike.setSelected(false);
                mTvCountLike.setTextColor(Color.GRAY);
            } else if (countLike < 0) {
                mBtnLike.setSelected(false);
                mBtnDislike.setSelected(true);
                mTvCountLike.setTextColor(Color.RED);
            } else {
                mBtnLike.setSelected(true);
                mBtnDislike.setSelected(false);
                mTvCountLike.setTextColor(Color.GREEN);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnLike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onLikeClick(getAdapterPosition());
                        break;
                    }
                case R.id.btnDislike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onDislikeClick(getAdapterPosition());
                        break;
                    }
            }
        }
    }
}
