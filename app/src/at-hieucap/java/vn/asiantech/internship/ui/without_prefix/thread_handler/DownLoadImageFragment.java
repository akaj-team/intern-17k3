package vn.asiantech.internship.ui.without_prefix.thread_handler;

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
import android.widget.Toast;

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
    int mDownloadedSize = 0;
    int mTotalSize = 0;
    private ProgressBar mProgressBar;
    private Button mBtnStart;
    private TextView mTvPercentage;
    private Bitmap mBitmap;
    private HttpURLConnection mUrlConnection;
    private InputStream mInputStream;
    private ByteArrayOutputStream mOutputStream;

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
            mUrlConnection = (HttpURLConnection) url.openConnection();
            mUrlConnection.connect();
            mInputStream = mUrlConnection.getInputStream();
            mOutputStream = new ByteArrayOutputStream();
            mTotalSize = mUrlConnection.getContentLength();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBar.setMax(mTotalSize);
                }
            });
            final byte[] buffer = new byte[1024];
            int bufferLength;
            while ((bufferLength = mInputStream.read(buffer)) > 0) {
                mOutputStream.write(buffer, 0, bufferLength);
                mDownloadedSize += bufferLength;
                getActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        mProgressBar.setProgress(mDownloadedSize);
                        float percentage = ((float) mDownloadedSize / mTotalSize) * 100;
                        mTvPercentage.setText(String.format(getString(R.string.download, mDownloadedSize, mTotalSize, percentage)) + "%)");
                    }
                });
            }
            mBitmap = BitmapFactory.decodeByteArray(mOutputStream.toByteArray(), 0, mOutputStream.size());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mBitmap == null) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.download_error),
                                Toast.LENGTH_LONG).show();
                    } else {
                        ((ShowImageFragment) ((DownloadActivity) getActivity()).mFragmentAdapter.getItem(1)).showPhoto(mBitmap);
                        ((DownloadActivity) getActivity()).setCurrentItem(1, true);
                    }
                }
            });
            mOutputStream.close();
        } catch (final IOException e) {
            Log.e(TAG, getString(R.string.disconnect_error));
        } finally {
            mUrlConnection.disconnect();
            if (mInputStream != null && mOutputStream != null) {
                try {
                    mInputStream.close();
                    mOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, getString(R.string.close_error));
                }
            }
        }
    }
}
