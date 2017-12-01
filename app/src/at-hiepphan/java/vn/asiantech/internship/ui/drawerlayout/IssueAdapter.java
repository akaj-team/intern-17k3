package vn.asiantech.internship.ui.drawerlayout;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Issue;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private List<Issue> mIssue;

    IssueAdapter(List<Issue> issueList){
        mIssue=issueList;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class IssueViewHolder extends ViewHolder{

        private ImageView mImgIcon;
        private TextView mTvIssueName;

        IssueViewHolder(final View itemView){
            super(itemView);
            mImgIcon = itemView.findViewById(R.id.imgIcon);
            mTvIssueName = itemView.findViewById(R.id.tvIssueName);
        }
    }
}
