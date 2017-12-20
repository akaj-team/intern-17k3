package vn.asiantech.internship.asynchronous;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/19/17.
 * Render 1 button, 1 ProgressBar
 */

public class Thread1Fragment extends Fragment {
    private Button mBtnClick;
    private int mTotalSize;
    int downloadedSize = 0;
    private TextView mTvResutlDownLoad;
    private ProgressBar mProgressBarDownLoadImg;

    public Thread1Fragment newInstance() {
        return new Thread1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1thread, container, false);
        initViews(view);
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downLoadPhoto();
                    }
                }).start();

            }
        });
        return view;
    }

    private void initViews(View view) {
        mBtnClick = view.findViewById(R.id.btnClick);
        mProgressBarDownLoadImg = view.findViewById(R.id.progressBarDownLoadImg);
    }

    private Bitmap downLoadPhoto() {
        try {
            URL url = new URL("https://images3.alphacoders.com/112/112347.jpg");
            HttpURLConnection urlConnection = (HttpURLConnection)
                    url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            File SDCardRoot = Environment.getExternalStorageDirectory();

            File file = new File(SDCardRoot, "downloaded_file.png");

            FileOutputStream fileOutput = new FileOutputStream(file);

            InputStream inputStream = urlConnection.getInputStream();

            mTotalSize = urlConnection.getContentLength();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressBarDownLoadImg.setMax(mTotalSize);
                }
            });
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                // update the progressbar //
                getActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    public void run() {
                        mProgressBarDownLoadImg.setProgress(downloadedSize);
                        float per = ((float) downloadedSize / mTotalSize) *
                                100;
                        mTvResutlDownLoad.setText("Downloaded " + downloadedSize + "KB / " + mTotalSize + "KB (" + (int) per + "%)");
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
