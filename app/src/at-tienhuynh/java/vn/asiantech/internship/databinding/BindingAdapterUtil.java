package vn.asiantech.internship.databinding;

import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by TienHuynh on 12/01/2018.
 * AsianTech Co., Ltd
 */
public class BindingAdapterUtil {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(CircleImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
