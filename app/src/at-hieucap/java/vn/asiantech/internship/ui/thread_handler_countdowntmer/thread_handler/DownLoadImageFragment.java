package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 20/12/2017.
 * Create first fragment
 */
public class DownLoadImageFragment extends Fragment {
    private final String TAG = DownLoadImageFragment.this.getClass().getSimpleName();
    int downloadedSize = 0;
    int totalSize = 0;
    private ProgressBar mProgressBar;
    private Button mBtnStart;
    private TextView mTvPercentage;
    private Bitmap myBitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_down_load_image, container, false);
        initViews(view);
        initData();
        addListener();
        return view;
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.progressBar);
        mTvPercentage = view.findViewById(R.id.tvPercentage);
        mBtnStart = view.findViewById(R.id.btnStart);
    }

    private void initData() {
        mProgressBar.setProgress(0);
    }

    private void addListener() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        downloadImage();
                    }
                });
                thread.start();
                mBtnStart.setEnabled(false);
            }
        });
    }

    /**
     * Create down load image from internet
     */
    void downloadImage() {
        try {
            String urlImage = "https://i2-prod.manchestereveningnews.co.uk/incoming/article1736623.ece/ALTERNATES/s1227b/Paul%20Scholes.jpg";
            URL url = new URL(urlImage);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            final InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            totalSize = urlConnection.getContentLength();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBar.setMax(totalSize);
                }
            });
            final byte[] buffer = new byte[10240];
            int bufferLength;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                output.write(buffer, 0, bufferLength);
                myBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.size());
                downloadedSize += bufferLength;
                getActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        mProgressBar.setProgress(downloadedSize);
                        float percentage = ((float) downloadedSize / totalSize) * 100;
                        mTvPercentage.setText(String.format(getString(R.string.download, downloadedSize, totalSize, percentage)) + "%)");
                    }
                });
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ShowImageFragment) ((DownloadActivity) getActivity()).mFragmentAdapter.getItem(1)).showPhoto(myBitmap);
                    ((DownloadActivity) getActivity()).setCurrentItem(1, true);
                }
            });
        } catch (final IOException e) {
            Log.e(TAG, getString(R.string.text_error));
        }
    }
}
