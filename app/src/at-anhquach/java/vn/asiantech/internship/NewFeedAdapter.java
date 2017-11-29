package vn.asiantech.internship;

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
    private OnButtonClick mOnButtonClick;

    NewFeedAdapter(List<NewFeed> newFeedList) {
        mNewFeedList = newFeedList;
    }

    @Override
    public NewFeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_newfeed, viewGroup, false);
        return new NewFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewFeedAdapter.NewFeedViewHolder holder, int i) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mNewFeedList.size();
    }

    class NewFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private TextView mTvStatus;
        private ImageView mImgLike;
        private ImageView mImgDisLike;
        private TextView mTvSumReact;

        public NewFeedViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mTvSumReact = itemView.findViewById(R.id.tvSumReact);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mImgDisLike = itemView.findViewById(R.id.imgDisLike);
            mImgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnButtonClick != null) {
                        mOnButtonClick.onClickLike(getAdapterPosition());
                    }
                }
            });
            mImgDisLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnButtonClick != null) {
                        mOnButtonClick.onClickDisLike(getAdapterPosition());
                    }
                }
            });
        }

        private void onBindData() {
            NewFeed newFeed = mNewFeedList.get(getAdapterPosition());
            mTvName.setText(newFeed.getName());
            mTvStatus.setText(newFeed.getStatus());
            mTvSumReact.setText();
        }
    }

    protected interface OnButtonClick {
        void onClickLike(int position);

        void onClickDisLike(int position);
    }
}
