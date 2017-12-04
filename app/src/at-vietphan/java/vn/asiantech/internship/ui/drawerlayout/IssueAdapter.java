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
        } else {
            return ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case HEADER:
                View itemView0 = layoutInflater.inflate(R.layout.row_header, parent, false);
                return new HeaderHolder(itemView0);
            case ITEM:
                View itemView1 = layoutInflater.inflate(R.layout.row_item, parent, false);
                return new ItemHolder(itemView1);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.onBindData();
                break;
            case ITEM:
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.onBindData(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mIssueList.size() + 1;
    }

    public interface OnItemClickListener {
        void onClickItemIssue(int position);
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImgAvatar;
        private TextView tvEmail;

        HeaderHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            circleImgAvatar = itemView.findViewById(R.id.circleImgAvater);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }

        private void onBindData() {
            circleImgAvatar.setImageResource(R.drawable.ic_account);
            tvEmail.setText(R.string.text_view_email);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView imgIssue;
        private TextView tvNameIssue;

        ItemHolder(final View itemView) {
            super(itemView);
            initViews();
            tvNameIssue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickItemIssue(getAdapterPosition());
                    }
                }
            });
        }

        private void initViews() {
            imgIssue = itemView.findViewById(R.id.imgIssue);
            tvNameIssue = itemView.findViewById(R.id.tvNameIssue);
        }

        private void onBindData(int position) {
            Issue issue = mIssueList.get(position - 1);
            imgIssue.setImageResource(issue.getIcon());
            tvNameIssue.setText(issue.getName());
        }
    }
}
