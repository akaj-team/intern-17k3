package vn.asiantech.internship.ui.asynchronous.fragments;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.asynchronous.activitys.ThreadHandleActivity;

/**
 * Created by vietphan on 20/12/2017.
 * Class OneFragment
 */
public class OneFragment extends Fragment implements View.OnClickListener {
    private static final int PROGRESS_BEGIN = 0;
    private static final int PROGRESS_END = 100;
    private Button mBtnClick;
    private Bitmap mBitmap;
    private ProgressBar mProgressBar;
    private TextView mTvStatus;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    private int mPercent;

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
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int imgLength = connection.getContentLength();
            long total = PROGRESS_BEGIN;
            final byte[] data = new byte[1024];
            int count;
            while ((count = is.read(data)) != -1) {
                total += count;
                mPercent = (int) ((total * PROGRESS_END) / imgLength);
                outputStream.write(data, PROGRESS_BEGIN, count);
                mHandler.post(mRunnable = new Runnable() {
                    @SuppressLint("SetTextI18n")
                    public void run() {
                        mProgressBar.setProgress(mPercent);
                        mTvStatus.setText(mPercent + getString(R.string.tren) + mProgressBar.getMax());
                        if (mPercent == PROGRESS_END) {
                            Toast.makeText(getContext(), R.string.downloaded, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        } catch (Throwable e) {
            e.getMessage();
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        if (mBitmap != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ThreadHandleActivity) getActivity()).sendMessageToTwoFragment(mBitmap);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }
}
