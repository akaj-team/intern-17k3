package vn.asiantech.internship.utils;

import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vietphan on 15/01/2018.
 * Class DataBindingLoadImage
 */
public class DataBindingLoadImage {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(CircleImageView circleImageView, String url) {
        Glide.with(circleImageView.getContext()).load(url).into(circleImageView);
    }
}
