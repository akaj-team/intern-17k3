package vn.asiantech.internship.ui.databinding;

import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hoangnhat on 15/01/2018.
 * Adapter Util
 */

public class BindingAdapterUtil {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(CircleImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
