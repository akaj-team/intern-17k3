package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

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
public class SecondFragment extends Fragment {
    private ImageView mImgResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_w5, container, false);
        mImgResult = view.findViewById(R.id.imgResult);
//        byte[] byteArray = getArguments().getByteArray("image");
//        if (byteArray == null) {
//            Toast.makeText(getActivity(), "Downloading Image. Please wait",
//                    Toast.LENGTH_LONG).show();
//        } else {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            mImgResult.setImageBitmap(bitmap);
//        }
        return view;
    }
}
