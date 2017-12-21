package vn.asiantech.internship.ui.asynchronous.fragments;

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
 * Created by vietphan on 20/12/2017.
 * Class TwoFragment
 */g
public class TwoFragment extends Fragment {
    private ImageView mImgInternet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        initviews(view);
        return view;
    }

    private void initviews(View view) {
        mImgInternet = view.findViewById(R.id.imgInternet);
    }

    public void receiveMessage(Bitmap bitmap) {
        mImgInternet.setImageBitmap(bitmap);
    }
}
