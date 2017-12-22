package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 20/12/2017.
 * Create second fragment
 */
public class ShowImageFragment extends Fragment {
    private ImageView mImgResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_image, container, false);
        mImgResult = view.findViewById(R.id.imgResult);
        return view;
    }

    public void showPhoto(Bitmap bitmap) {
        mImgResult.setImageBitmap(bitmap);
    }
}
