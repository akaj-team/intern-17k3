package vn.asiantech.internship.ui.thread_handler.ui.thread;


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
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private TextView tvPercent;
    private ProgressBar mProgressBar;
    private Button mBtnStart;
    private View mView;
    public int mStartProgessBar = 0;
    public int mFinishProgessBar = 100;
    private int mStatus = 0;
    public Bitmap mBitmap;
    Thread mThread;
    private Handler mProgressBarHandler = new Handler();
    private static String mImageUrl = "https://i.ytimg.com/vi/Orh592OBoKY/maxresdefault.jpg";

    public DownloadFragment() {
        //No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_download, container, false);
        initView();
        initData();
        initListener();
        return mView;
    }

    /**
     * initView
     */
    private void initView() {
        tvPercent = mView.findViewById(R.id.tvPercentage);
        mProgressBar = mView.findViewById(R.id.progressBar);
        mBtnStart = mView.findViewById(R.id.btnStart);
    }

    /**
     * initData for ProgressBar
     */
    private void initData() {
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
    }

    /**
     * initListener for Button
     */
    private void initListener() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDownloadImage();
                getBitmapFromURL(mImageUrl);

            }
        });
    }

    /**
     * initDownloadImage and get percent progressBar
     */
    private void initDownloadImage() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mStatus < 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.d("e1", e.getMessage());
                    }
                    mProgressBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mStatus);
                            tvPercent.setText(String.valueOf(mStatus).concat("%"));
                        }
                    });
                }
                if (mStatus >= 100) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.d("e", e.getMessage());
                    }
                    mStatus = 0;
                }
            }
        });
        mThread.start();
    }

    /**
     * get Bitmap from URL
     */
    public void getBitmapFromURL(final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream input = null;
                ByteArrayOutputStream output = null;
                try {
                    URL url = new URL(imageUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    int fileLength = connection.getContentLength();
                    input = connection.getInputStream();
                    output = new ByteArrayOutputStream();
                    byte data[] = new byte[512];
                    long total = mStartProgessBar;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                        mStatus = (int) (total * mFinishProgessBar / fileLength);
                        mBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.size());
                    }
                } catch (IOException e) {
                    Log.d("e", e.getMessage());
                } finally {
                    assert connection != null;
                    connection.disconnect();
                    try {
                        assert input != null;
                        input.close();
                        assert output != null;
                        output.close();
                    } catch (IOException e) {
                        Log.d("error :", e.getMessage());
                    }
                }
                if (mBitmap != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ThreadActivity) getActivity()).setBitMap(mBitmap);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThread.stop();
    }


}
