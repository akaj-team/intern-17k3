package vn.asiantech.internship.asynchronous;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/20/17.
 * Create adapter for 1 rectcler view
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private List<String> mStrs;

    RecyclerViewAdapter(List<String> strs){
        mStrs = strs;
    }
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new RecyclerViewViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerViewViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mStrs.size();
    }
    class RecyclerViewViewHolder extends RecyclerView.ViewHolder{
        private Button mBtnItem;

        RecyclerViewViewHolder(View itemView) {
            super(itemView);
            mBtnItem = itemView.findViewById(R.id.btnItem);
        }
        private void onBindData(){
            mBtnItem.setText(mStrs.get(getAdapterPosition()));
        }
    }
}
