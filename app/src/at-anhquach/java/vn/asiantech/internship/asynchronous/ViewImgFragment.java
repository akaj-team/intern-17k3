package vn.asiantech.internship.asynchronous;

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
 * Created by anh.quach on 12/19/17.
 * Render 1 button, 1 ProgressBar
 */

public class ViewImgFragment extends Fragment {
    private ImageView mImgDownLoadedImg;

    public ViewImgFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2thread, container, false);
        mImgDownLoadedImg = view.findViewById(R.id.imgDownLoaded);
        return view;
    }

    public void showPhoto(Bitmap bitmap) {
        mImgDownLoadedImg.setImageBitmap(bitmap);
    }
}
