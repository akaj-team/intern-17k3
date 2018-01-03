package vn.asiantech.internship.threadandhandler.dowloadimage.ui;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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
 * DownLoadImageFragment to downloading image from internet
 */
public class DownLoadImageFragment extends Fragment {
    private static final String LINK_IMAGE = "https://s-media-cache-ak0.pinimg.com/originals/96/bb/17/96bb17f887be41927c834479fd7590b2.jpg";
    private static final int START_PROGRESS = 0;
    private static final int FINISH_PROGRESS = 100;
    private int mStatus = 0;
    private View mView;
    private ProgressBar mProgressBarDownLoad;
    private Handler mProgressBarHandler = new Handler();
    private TextView mTvPercentDownLoad;
    private Button mBtnStartDownLoad;
    private static Bitmap mBitmap;

    public DownLoadImageFragment() {
        // No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_down_load_image, container, false);
        initViews();
        initData();
        initListener();
        return mView;
    }

    /**
     * Init Views
     */
    private void initViews() {
        mProgressBarDownLoad = mView.findViewById(R.id.progressBarDownloadImage);
        mTvPercentDownLoad = mView.findViewById(R.id.tvPercentDownLoad);
        mBtnStartDownLoad = mView.findViewById(R.id.btnStartDownLoad);
    }

    /**
     * Init Data to Progress bar
     */
    private void initData() {
        mProgressBarDownLoad.setProgress(START_PROGRESS);
        mProgressBarDownLoad.setMax(FINISH_PROGRESS);
    }

    /**
     * Init Listener for Button
     */
    private void initListener() {
        mBtnStartDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initProgressBar();
                downloadPhoto(LINK_IMAGE);
            }
        });
    }

    /**
     * This method is used to DownLoad Image From The Internet
     */
    private void downloadPhoto(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream input = null;
                ByteArrayOutputStream output = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    input = connection.getInputStream();
                    output = new ByteArrayOutputStream();
                    int fileLength = connection.getContentLength();
                    byte data[] = new byte[1024];
                    long total = START_PROGRESS;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // Publish the progress
                        mStatus = (int) ((total) / fileLength) * FINISH_PROGRESS;
                        output.write(data, 0, count);
                    }
                    // assign bitmap
                    mBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.toByteArray().length);
                } catch (IOException e) {
                    Log.d("error", e.getMessage());
                } finally {
                    connection.disconnect();
                    try {
                        input.close();
                        output.close();
                    } catch (IOException e) {
                        Log.d("error close :", e.getMessage());
                    }

                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mBitmap != null) {
                            if (getActivity() instanceof TabManagementActivity) {
                                ((ViewImageFragment) ((TabManagementActivity) getActivity()).mTabAdapter.getItem(1)).showBitmap(mBitmap);
                            }
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * this method to init percent in progress bar
     */
    private void initProgressBar() {
        mBtnStartDownLoad.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mStatus < FINISH_PROGRESS) {
                    // use handler to update percent download
                    mProgressBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBarDownLoad.setProgress(mStatus);
                            mTvPercentDownLoad.setText(String.valueOf(mStatus).concat("%"));
                        }
                    });
                }
            }
        }).start();
    }
}
