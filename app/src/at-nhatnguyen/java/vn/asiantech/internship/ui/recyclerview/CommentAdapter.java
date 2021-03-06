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
import vn.asiantech.internship.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> mComments;
    private OnItemClickListener mOnItemClickListener;

    CommentAdapter(List<Comment> comments, OnItemClickListener onItemClickListener) {
        mComments = comments;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public interface OnItemClickListener {
        void onClickLike(int position);

        void onClickDislike(int position);
    }

    class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvName;
        private TextView mTvCommentContent;
        private TextView mTvTotalLike;
        private Button mBtnLike;
        private Button mBtnDislike;

        CommentViewHolder(final View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvCommentContent = itemView.findViewById(R.id.tvCommentContent);
            mTvTotalLike = itemView.findViewById(R.id.tvTotalLike);
            mBtnLike = itemView.findViewById(R.id.btnLike);
            mBtnDislike = itemView.findViewById(R.id.btnDislike);
            mBtnLike.setOnClickListener(this);
            mBtnDislike.setOnClickListener(this);
        }

        private void onBindData() {
            Comment comment = mComments.get(getAdapterPosition());
            mTvName.setText(comment.getName());
            mTvCommentContent.setText(comment.getContent());
            if (comment.getTotalLike() > 0) {
                mBtnLike.setSelected(true);
                mBtnDislike.setSelected(false);
                mTvTotalLike.setTextColor(Color.GREEN);
            }
            if (comment.getTotalLike() < 0) {
                mBtnLike.setSelected(false);
                mBtnDislike.setSelected(true);
                mTvTotalLike.setTextColor(Color.RED);
            }
            if (comment.getTotalLike() == 0) {
                mBtnLike.setSelected(false);
                mBtnDislike.setSelected(false);
                mTvTotalLike.setTextColor(Color.GRAY);
            }
            mTvTotalLike.setText(String.valueOf(comment.getTotalLike()));
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnLike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickLike(getAdapterPosition());
                    }
                    break;
                case R.id.btnDislike:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickDislike(getAdapterPosition());
                    }
                    break;
            }
        }
    }
}
