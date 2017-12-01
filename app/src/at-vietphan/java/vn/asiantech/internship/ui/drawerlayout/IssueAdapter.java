package vn.asiantech.internship.ui.drawerlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;

public class IssueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private List<Issue> mIssueList;
    private Context mContext;

    IssueAdapter(List<Issue> mIssueList, Context mContext) {
        this.mIssueList = mIssueList;
        this.mContext = mContext;
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
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
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
                headerHolder.circleImgAvatar.setImageResource(R.drawable.ic_account);
                headerHolder.tvEmail.setText(R.string.text_view_email);
                break;
            case ITEM:
                Issue issue = mIssueList.get(position - 1);
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.imgIssue.setImageResource(issue.getIcon());
                itemHolder.tvNameIssue.setText(issue.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mIssueList.size() + 1;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImgAvatar;
        private TextView tvEmail;

        HeaderHolder(View itemView) {
            super(itemView);
            circleImgAvatar = itemView.findViewById(R.id.circleImgAvater);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView imgIssue;
        private TextView tvNameIssue;

        ItemHolder(View itemView) {
            super(itemView);
            imgIssue = itemView.findViewById(R.id.imgIssue);
            tvNameIssue = itemView.findViewById(R.id.tvNameIssue);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, mIssueList.get(getAdapterPosition() - 1).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
