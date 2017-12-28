package vn.asiantech.internship.ui.thread.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * A simple {@link Fragment} subclass.
 * This fragment use for display image
 */
public class TwoFragment extends Fragment {
    private ImageView mImgView;

    @NonNull
    public static TwoFragment newInstance() {
        return new TwoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mImgView = view.findViewById(R.id.imgPhoto);
    }

    public void showBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            mImgView.setImageBitmap(bitmap);
        }
    }
}
