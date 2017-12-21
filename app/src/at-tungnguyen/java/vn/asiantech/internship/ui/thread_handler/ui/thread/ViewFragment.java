package vn.asiantech.internship.ui.thread_handler.ui.thread;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {
    private ImageView mImgLoadImage;
    private View mView;

    public static ViewFragment newInstance() {
        ViewFragment fragment = new ViewFragment();
        return fragment;
    }

    public ViewFragment() {
        //No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_view, container, false);
        initView();
        return mView;
    }

    public void setImageBitmapp(Bitmap bitmap) {
        mImgLoadImage.setImageBitmap(bitmap);
    }

    private void initView() {
        mImgLoadImage = mView.findViewById(R.id.imgBitmapView);
    }


}
