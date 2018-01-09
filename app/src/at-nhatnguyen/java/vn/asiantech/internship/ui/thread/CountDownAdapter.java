package vn.asiantech.internship.ui.thread;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by hoangnhat on 21/12/2017.
 * Adapter for Countdown timer
 */
public class CountDownAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> mNumber;

    CountDownAdapter(List<Integer> number) {
        this.mNumber = number;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_countdown_time, parent, false);
        return new CountDownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mNumber.get(position) != null) {
            ((CountDownViewHolder) holder).onBindData(mNumber.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNumber.size();
    }

    /**
     * Class is used for display item
     */
    private static class CountDownViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvCountDown;

        CountDownViewHolder(View itemView) {
            super(itemView);
            mTvCountDown = itemView.findViewById(R.id.tvCountDown);
        }

        private void onBindData(Integer integer) {
            mTvCountDown.setText(String.valueOf(integer));
        }
    }
}
