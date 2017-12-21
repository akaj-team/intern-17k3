package vn.asiantech.internship.ui.asynchronous.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.asynchronous.activitys.ThreadHandleActivity;
import vn.asiantech.internship.ui.asynchronous.adapters.ThreadHandleAdapter;

/**
 * Created by vietphan on 20/12/2017.
 * Class OneFragment
 */
public class OneFragment extends Fragment implements View.OnClickListener {
    private Button mBtnClick;
    private ImageView mImgTest;
    private Bitmap mBitmap;
    private ProgressBar mProgressBar;
    private int mProgressBarStatus = 0;
    private TextView mTvStatus;
    private Handler mHandler = new Handler();
    private ThreadHandleAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initViews(view);
        initListener();
        return view;
    }

    private void initViews(View view) {
        mBtnClick = view.findViewById(R.id.btnClick);
        mImgTest = view.findViewById(R.id.imgTest);
        mProgressBar = view.findViewById(R.id.progressBar);
        mTvStatus = view.findViewById(R.id.tvStatus);
    }

    private void initListener() {
        mBtnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadPhoto();
            }

        }).start();
    }

    private void downloadPhoto() {
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            String imageUrl = "https://wallpaperscraft.com/image/mountains_reeds_grass_sky_109004_2048x1259.jpg";
            connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            mBitmap = BitmapFactory.decodeStream(is);
            URL url = new URL(imageUrl);
            long total = 0;
            int imgLength = connection.getContentLength();
            InputStream inputStream = new BufferedInputStream(url.openStream());
            byte[] data = new byte[4096];
            int count;
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                final int percent = (int) ((total * 100) / imgLength);
                mHandler.post(new Runnable() {
                    public void run() {
                        mProgressBar.setProgress(percent);
                        mTvStatus.setText(percent + "/" + mProgressBar.getMax());

                    }
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.disconnect();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mBitmap != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new ThreadHandleAdapter(getFragmentManager());
                    ((ThreadHandleActivity) getActivity()).sendMessageToB(mBitmap);
                }
            });
        }
    }
}
