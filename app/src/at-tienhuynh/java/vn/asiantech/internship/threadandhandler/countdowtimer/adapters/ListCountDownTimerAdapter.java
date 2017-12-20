package vn.asiantech.internship.threadandhandler.countdowtimer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.threadandhandler.countdowtimer.models.Item;

/**
 * Created at 2017
 * Created by jackty on 20/12/2017.
 */
public class ListCountDownTimerAdapter extends RecyclerView.Adapter<ListCountDownTimerAdapter.ListCountDownViewHolder> {

    private List<Item> mItemLists;

    public ListCountDownTimerAdapter(List<Item> itemLists) {
        mItemLists = itemLists;
    }

    @Override
    public ListCountDownViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_countdown_timer, parent, false);
        return new ListCountDownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListCountDownViewHolder holder, int position) {
        holder.onBindData(position);
    }

    @Override
    public int getItemCount() {
        return mItemLists.size();
    }

    /**
     * This is inner class. It's used to bind data and set values to recycler view
     */
    class ListCountDownViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvItem;

        ListCountDownViewHolder(View itemView) {
            super(itemView);
            mTvItem = itemView.findViewById(R.id.tvItemCountDownTimer);
        }

        private void onBindData(int position) {
            Item item = mItemLists.get(position);
            if (item != null) {
                mTvItem.setText(item.getNameItem().concat(String.valueOf(position)));
            }
        }
    }
}
