package vn.asiantech.internship.ui.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Issue;

public class DrawerLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Issue> mIssueList;
    private OnItemClickListener mOnItemClickListener;

    private static final int HEADER = 0;
    private static final int ITEM = 1;

    DrawerLayoutAdapter(List<Issue> issue, OnItemClickListener onItemClickListener) {
        mIssueList = issue;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case HEADER:
                View viewHeader = layoutInflater.inflate(R.layout.item_header_issue, viewGroup, false);
                return new HeaderViewHolder(viewHeader);
            case ITEM:
                View viewItem = layoutInflater.inflate(R.layout.item_issue, viewGroup, false);
                return new ItemViewHolder(viewItem);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.onBindDataHeader();
                break;
            case ITEM:
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
            return HEADER;
        } else {
            return ITEM;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCircleImgAvata;
        private TextView mTvEmail;

        HeaderViewHolder(View itemView) {
            super(itemView);
            mCircleImgAvata = itemView.findViewById(R.id.circleImgAvata);
            mTvEmail = itemView.findViewById(R.id.tvEmail);
        }

        private void onBindDataHeader() {
            mCircleImgAvata.setImageResource(R.drawable.img_avata);
        }

    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgIcon;
        private TextView mTvNameIssue;

        ItemViewHolder(View itemView) {
            super(itemView);
            mImgIcon = itemView.findViewById(R.id.imgIcon);
            mTvNameIssue = itemView.findViewById(R.id.tvNameIssue);
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
            Issue issue = mIssueList.get(getAdapterPosition() - 1);
            mImgIcon.setImageResource(issue.getIcon());
            mTvNameIssue.setText(issue.getName());
        }
    }

    public interface OnItemClickListener {
        void onNameIssueClick(int position);
    }
}
