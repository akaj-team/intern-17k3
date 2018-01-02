package vn.asiantech.internship.asynchronous;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 21/12/2560.
 * Adapter for recyclerView Item countdown
 */
public class CountdownTimerAdapter extends RecyclerView.Adapter<CountdownTimerAdapter.ItemViewHolder> {
    private List<String> mItemList = new ArrayList<>();

    CountdownTimerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_countdown, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.onBindData(mItemList, position);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * Class ItemViewHolder
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvItemCountdown;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTvItemCountdown = itemView.findViewById(R.id.tvItemCountdown);
        }

        private void onBindData(List<String> listItem, int position) {
            String text = listItem.get(position);
            if (text != null) {
                mTvItemCountdown.setText(text);
            }
        }
    }
}
