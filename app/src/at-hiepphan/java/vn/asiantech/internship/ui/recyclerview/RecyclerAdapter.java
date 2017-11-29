package vn.asiantech.internship.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.Data;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<Data> mListData;

    public RecyclerAdapter(List<Data> data){
        mListData=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUserName;
        private TextView tvStatus;
        private TextView tvSumLike;
        private Button btnLikeClicked;
        private Button btnLikeDefault;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvSumLike= itemView.findViewById(R.id.tvSumLike);
        }
    }
}
