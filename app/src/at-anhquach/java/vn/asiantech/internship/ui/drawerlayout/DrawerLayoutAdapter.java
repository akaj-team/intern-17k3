package vn.asiantech.internship.ui.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Issue;

public class DrawerLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYE_ITEM = 1;
    private List<Issue> mIssueList;
    private OnItemClickListener mOnItemClickListener;

    DrawerLayoutAdapter(List<Issue> issues, OnItemClickListener onItemClickListener) {
        mIssueList = issues;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case TYPE_HEADER:
                View viewHeader = layoutInflater.inflate(R.layout.item_header_issue, viewGroup, false);
                return new HeaderViewHolder(viewHeader);
            case TYE_ITEM:
                View viewItem = layoutInflater.inflate(R.layout.item_issue, viewGroup, false);
                return new ItemViewHolder(viewItem);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.onBindDataHeader();
                break;
            case TYE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.onBindDataItem();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mIssueList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYE_ITEM;
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCircleImgAvatar;

        HeaderViewHolder(View itemView) {
            super(itemView);
            mCircleImgAvatar = itemView.findViewById(R.id.circleImgAvatar);
        }

        private void onBindDataHeader() {
            mCircleImgAvatar.setImageResource(R.drawable.img_avatar);
        }

    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNameIssue;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTvNameIssue = itemView.findViewById(R.id.tvIssueName);
            mTvNameIssue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onNameIssueClick(getAdapterPosition());
                    }
                }
            });
        }

        private void onBindDataItem() {
            Issue issues = mIssueList.get(getAdapterPosition() - 1);
            mTvNameIssue.setCompoundDrawablesWithIntrinsicBounds(issues.getIcon(), 0, 0, 0);
            mTvNameIssue.setText(issues.getName());
        }
    }

    public interface OnItemClickListener {
        void onNameIssueClick(int position);
    }
}
