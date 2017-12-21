package vn.asiantech.internship.ui.asynchronous.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.CountDownTimerItem;

/**
 * Created by vietphan on 21/12/2017.
 * Class CountDownTimerAdapter
 */
public class CountDownTimerAdapter extends RecyclerView.Adapter<CountDownTimerAdapter.CountDownTimerHolder> {
    private List<CountDownTimerItem> mCountDownTimerItems;

    public CountDownTimerAdapter(List<CountDownTimerItem> countDownTimers) {
        this.mCountDownTimerItems = countDownTimers;
    }

    @Override
    public CountDownTimerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_countdowntimer, parent, false);
        return new CountDownTimerHolder(view);
    }

    @Override
    public void onBindViewHolder(CountDownTimerHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mCountDownTimerItems.size();
    }

    class CountDownTimerHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle;

        CountDownTimerHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View view) {
            tvTittle = view.findViewById(R.id.tvTittle);
        }

        private void onBindData() {
            if (mCountDownTimerItems == null) {
                Log.d("item", "list is null");
            } else {
                CountDownTimerItem item = mCountDownTimerItems.get(getAdapterPosition());
                tvTittle.setText(item.getTitle());
            }
        }
    }
}
