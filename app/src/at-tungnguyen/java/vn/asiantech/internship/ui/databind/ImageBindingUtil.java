package vn.asiantech.internship.ui.databind;

import android.content.Context;
import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 16/01/2018.
 */
public class ImageBindingUtil {
    @BindingAdapter("imageUrl")
    public static void loadImage(CircleImageView view, String url) {
        Context context = view.getContext();
        Glide.with(view.getContext()).load(url).into(view);
    }
}
