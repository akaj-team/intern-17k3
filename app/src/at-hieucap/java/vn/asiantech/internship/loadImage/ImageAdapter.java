package vn.asiantech.internship.loadImage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import vn.asiantech.internship.R;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemImage> mList;

    ImageAdapter(List<ItemImage> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TestViewHolder) {
            ((TestViewHolder) holder).binData();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgItem;
        private Button mBtnLoadImage;

        TestViewHolder(View itemView) {
            super(itemView);
            mBtnLoadImage = itemView.findViewById(R.id.btnLoadImage);
            mImgItem = itemView.findViewById(R.id.imgItem);
        }

        private void binData() {
            mImgItem.setImageResource(mList.get(getAdapterPosition()).getImage());
        }
    }
}
