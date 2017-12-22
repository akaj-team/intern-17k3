package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 19/12/2560.
 * Fragment show image downloaded
 */
public class ShowImageFragment extends Fragment {
    private ImageView mImgDownloaded;

    public ShowImageFragment() {
        // No - oop
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_show_image, container, false);
        mImgDownloaded = v.findViewById(R.id.imgImageDownloaded);
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            mImgDownloaded.setImageBitmap(((HandlerThreadActivity) getActivity()).getmBitmap());
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
