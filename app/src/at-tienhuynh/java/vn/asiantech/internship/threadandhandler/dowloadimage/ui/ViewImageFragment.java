package vn.asiantech.internship.threadandhandler.dowloadimage.ui;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * ViewImageFragment to show image downloaded at fragment one
 */
public class ViewImageFragment extends Fragment {

    public static ImageView mImgDownLoaded;
    private View mView;

    public ViewImageFragment() {
        //No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_view_image, container, false);
        initViews();
        return mView;
    }

    /**
     * Init Views
     */
    private void initViews() {
        mImgDownLoaded = mView.findViewById(R.id.imgDownLoaded);
    }

    /**
     * this method used to set bitmap to image view
     */
    public void showBitmap(Bitmap bitmap) {
        mImgDownLoaded.setImageBitmap(bitmap);
    }
}
