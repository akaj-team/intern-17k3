package vn.asiantech.internship.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.Content;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Content> mContents;

    public RecyclerAdapter(List<Content> contentList) {
        mContents = contentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvStatus;
        private TextView tvLikeCount;
        private Button btnLike;
        private Button btnDisLike;

        private ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnDisLike = itemView.findViewById(R.id.btnDisLike);
            btnDisLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int likeCount = Integer.parseInt(tvLikeCount.getText().toString().trim());
                    checkLike(--likeCount);

                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int likeCount = Integer.parseInt(tvLikeCount.getText().toString());
                    checkLike(++likeCount);
                }
            });
        }

        private void onBindData() {
            Content content = mContents.get(getAdapterPosition());
            tvLikeCount.setText(String.valueOf(content.getLikeCount()));
            tvUserName.setText(content.getName());
            tvStatus.setText(content.getStatus());
            int likeCount = Integer.parseInt(tvLikeCount.getText().toString().trim());
            checkLike(likeCount);
        }

        private void checkLike(int likeCount) {
            tvLikeCount.setText(String.valueOf(likeCount));
            if (likeCount == 0) {
                btnDisLike.setBackgroundResource(R.drawable.ic_dislike_default);
                btnLike.setBackgroundResource(R.drawable.ic_like_default);
                return;
            }
            if (likeCount > 0) {
                btnLike.setBackgroundResource(R.drawable.ic_like_clicked);
                return;
            }
            if (likeCount < 0) {
                btnDisLike.setBackgroundResource(R.drawable.ic_dislike_clicked);
            }
        }
    }
}
