package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
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

    public DownLoadImageFragment newInstance() {
        return new DownLoadImageFragment();
    }

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
        mProgressBar.setMax(100);
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
            String urlImage = "http://www.hdwallpaper.nu/wp-content/uploads/2016/01/zlatan_ibrahimovic_wallpaper_93603.jpg";
            URL url = new URL(urlImage);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            final InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            final Message message = new Message();
            message.obj = inputStream;
            totalSize = urlConnection.getContentLength();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBar.setMax(totalSize);
                }
            });
            final byte[] buffer = new byte[1024];
            int bufferLength;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                output.write(buffer, 0, bufferLength);
                myBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.size());
                downloadedSize += bufferLength;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(downloadedSize);
                        float percentage = ((float) downloadedSize / totalSize) * 100;
                        mTvPercentage.setText(getString(R.string.download, downloadedSize, totalSize, (int) percentage));
                    }
                });
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ShowImageFragment) ((DownloadActivity) getActivity()).mFragmentAdapter.getItem(1)).showPhoto(myBitmap);
                }
            });
        } catch (final IOException e) {
            Log.e(TAG, getString(R.string.text_error));
        }
    }
}
