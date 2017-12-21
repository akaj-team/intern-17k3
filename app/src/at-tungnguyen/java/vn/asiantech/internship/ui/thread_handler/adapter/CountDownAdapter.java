package vn.asiantech.internship.ui.thread_handler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.CountDownItem;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 20/12/2017.
 */

public class CountDownAdapter extends RecyclerView.Adapter<CountDownAdapter.ViewHolder> {
    private List<CountDownItem> mCountDownItems;

    public CountDownAdapter(List<CountDownItem> mCountDownItems) {
        this.mCountDownItems = mCountDownItems;
    }

    @Override
    public CountDownAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countdown, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountDownAdapter.ViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mCountDownItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvItem);
        }

        public void onBindData() {
            CountDownItem countDownItem = mCountDownItems.get(getAdapterPosition());
            mTvName.setText(countDownItem.getName().concat(String.valueOf(getAdapterPosition())));
        }
    }
}
