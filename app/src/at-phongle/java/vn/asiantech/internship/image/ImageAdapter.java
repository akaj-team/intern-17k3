package vn.asiantech.internship.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Image;

/**
 * Created by phongle on 12/12/2560.
 * Adapter RecyclerView Image
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Image> mImageList;
    private Context mContext;

    ImageAdapter(List<Image> imageList, Context context) {
        mImageList = imageList;
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        holder.bindData(mImageList, position, mContext);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    /**
     * Created by phongle on 12/12/2560.
     * Class ImageViewHolder
     */
    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgImage;
        private TextView mTvInformationImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mImgImage = itemView.findViewById(R.id.imgImage);
            mTvInformationImage = itemView.findViewById(R.id.tvInformationImage);
        }

        private void bindData(List<Image> imageList, int position, Context context) {
            Image image = imageList.get(position);
            if (image.getImageDrawerble() != 0) {
                Picasso.with(context)
                        .load(image.getImageDrawerble())
                        .resize(64, 64)
                        .centerCrop()
                        .into(mImgImage);
            } else {
                Picasso.with(context)
                        .load(image.getImageUrl())
                        .resize(64, 64)
                        .centerCrop()
                        .into(mImgImage);
            }
            mTvInformationImage.setText(image.getInformation());
        }
    }
}
