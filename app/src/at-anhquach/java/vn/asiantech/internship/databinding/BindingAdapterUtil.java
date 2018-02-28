package vn.asiantech.internship.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by anh.quach on 1/15/18.
 * Binding Adapter
 */
public class BindingAdapterUtil {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
