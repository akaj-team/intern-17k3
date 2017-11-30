package vn.asiantech.internship.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Posts;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Posts> mPostList;
    private OnItemClickListener mOnItemClickListener;

    PostAdapter(List<Posts> mPostList, OnItemClickListener mOnItemClickListener) {
        this.mPostList = mPostList;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.onBindData(holder.mImgDislike.getContext());
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvSubject;
        private ImageView mImgLike;
        private ImageView mImgDislike;
        private TextView mTvLikeNumber;
        private TextView mTvDescription;

        PostViewHolder(final View itemView) {
            super(itemView);
            initViews();
            initListener();
        }

        private void initViews() {
            mTvSubject = itemView.findViewById(R.id.tvSubject);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDislike = itemView.findViewById(R.id.imgDislike);
            mTvLikeNumber = itemView.findViewById(R.id.tvLikeNumber);
            mTvDescription = itemView.findViewById(R.id.tvDescription);
        }

        private void initListener() {
            mImgLike.setOnClickListener(this);
            mImgDislike.setOnClickListener(this);
        }

        private void onBindData(Context context) {
            Posts posts = mPostList.get(getAdapterPosition());
            mTvSubject.setText(posts.getSubject());
            mTvLikeNumber.setText(String.valueOf(posts.getLikeNumber()));
            mTvDescription.setText(posts.getDescription());
            setColor(posts.getLikeNumber(), context);
        }

        private void setColor(int i, Context context) {
            if (i > 0) {
                mTvLikeNumber.setTextColor(context.getResources().getColor(R.color.greenLike));
                mImgLike.setSelected(true);
                mImgDislike.setSelected(false);
            } else if (i < 0) {
                mTvLikeNumber.setTextColor(context.getResources().getColor(R.color.redDislike));
                mImgDislike.setSelected(true);
                mImgLike.setSelected(false);
            } else {
                mTvLikeNumber.setTextColor(context.getResources().getColor(R.color.black));
                mImgDislike.setSelected(false);
                mImgLike.setSelected(false);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgLike:
                    mOnItemClickListener.onLikeClick(getAdapterPosition());
                    break;
                case R.id.imgDislike:
                    mOnItemClickListener.onDislikeClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onLikeClick(int position);

        void onDislikeClick(int position);
    }
}
