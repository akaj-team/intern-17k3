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
import vn.asiantech.internship.models.Issue;

/**
 * class IssueAdapter
 */
public class IssueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private List<Issue> mIssueList;
    private OnItemClickListener mOnItemClickListener;

    IssueAdapter(List<Issue> mIssueList, OnItemClickListener onItemClickListener) {
        this.mIssueList = mIssueList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        }
        return ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewHeader = layoutInflater.inflate(R.layout.row_header, parent, false);
        View viewItem = layoutInflater.inflate(R.layout.row_item, parent, false);
        switch (viewType) {
            case HEADER:
                HeaderHolder headerHolder = new HeaderHolder(viewHeader);
                headerHolder.circleImgAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onClickImgAvatar();
                        }
                    }
                });
                return headerHolder;
            default:
                final ItemHolder itemHolder = new ItemHolder(viewItem);
                viewItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onClickItemIssue(itemHolder.getAdapterPosition());
                        }
                    }
                });
                return itemHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.onBindData();
                break;
            default:
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.onBindData(position, mIssueList);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mIssueList.size() + 1;
    }

    /**
     * interface onItemClickListener handle click change avatar and items issue
     */
    public interface OnItemClickListener {
        void onClickItemIssue(int position);

        void onClickImgAvatar();
    }

    /**
     * holder for header in drawer layout
     */
    static class HeaderHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImgAvatar;
        private TextView tvEmail;

        HeaderHolder(View itemView) {
            super(itemView);
            circleImgAvatar = itemView.findViewById(R.id.circleImgAvater);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }

        private void onBindData() {
            circleImgAvatar.setImageResource(R.drawable.ic_account);
            tvEmail.setText(R.string.text_view_email);
        }
    }

    /**
     * holder for item in drawer layout
     */
    static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView imgIssue;
        private TextView tvNameIssue;

        ItemHolder(final View itemView) {
            super(itemView);
            imgIssue = itemView.findViewById(R.id.imgIssue);
            tvNameIssue = itemView.findViewById(R.id.tvNameIssue);
        }

        private void onBindData(int position, List<Issue> mIssueList) {
            Issue issue = mIssueList.get(position - 1);
            imgIssue.setImageResource(issue.getIcon());
            tvNameIssue.setText(issue.getName());
        }
    }
}
