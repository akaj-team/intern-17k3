package vn.asiantech.internship.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Status;

/**
 * Created at 2017
 * Created by jackty on 29/11/2017.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    private List<Status> mStatusList;

    public StatusAdapter(List<Status> statusList) {
        mStatusList = statusList;
    }

    @Override
    public StatusAdapter.StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.StatusViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    class StatusViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private TextView mTvDescription;
        private TextView mTvCountLike;

        StatusViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
            mTvDescription = itemView.findViewById(R.id.tvDescription);
            mTvCountLike = itemView.findViewById(R.id.tvNumCountLike);
        }
        private void onBindData() {
            Status status = mStatusList.get(getAdapterPosition());
            mTvTitle.setText(status.getTitle());
            mTvDescription.setText(status.getDescription());
            mTvCountLike.setText(""+status.getNumlike());
        }
    }
}
