package vn.asiantech.internship.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.DataContent;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<DataContent> mListData;

    public RecyclerAdapter(List<DataContent> mListData) {
        this.mListData = mListData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvUserName.setText(mListData.get(position).getName());
        holder.tvStatus.setText(mListData.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvStatus;
        private TextView tvSumLike;
        private Button btnLike;
        private Button btnUnLike;

        private ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvSumLike = itemView.findViewById(R.id.tvSumLike);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnUnLike = itemView.findViewById(R.id.btnUnLike);
            btnUnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int likeCount = Integer.parseInt(tvSumLike.getText().toString().trim());
                    checkLike(--likeCount);

                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int likeCount = Integer.parseInt(tvSumLike.getText().toString());
                    checkLike(++likeCount);
                }
            });
        }

        private void checkLike(int likeCount) {
            tvSumLike.setText(String.valueOf(likeCount));
            if (likeCount == 0) {
                btnUnLike.setBackgroundResource(R.drawable.ic_unlike_default);
                btnLike.setBackgroundResource(R.drawable.ic_unlike_clicked);
                return;
            }
            if (likeCount > 0) {
                btnLike.setBackgroundResource(R.drawable.ic_like_clicked);
                return;
            }
            if (likeCount < 0) {
                btnUnLike.setBackgroundResource(R.drawable.ic_like_default);
            }
        }
    }
}
