package vn.asiantech.internship.asynchronous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 19/12/2560.
 * Fragment show image downloaded
 */

public class ImageDownloadedFragment extends Fragment {
    public ImageView mImgDownloaded;

    public static Fragment getInstance() {
        return new ImageDownloadedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_image_downloaded, container, false);
        mImgDownloaded = v.findViewById(R.id.imgImageDownloaded);
        showImage();
        return v;
    }

    protected void showImage() {
        byte[] byteArray = getArguments().getByteArray("image");
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            mImgDownloaded.setImageBitmap(bitmap);
        } else {
            Log.d("vvv", "Download fail !");
        }
    }
}
