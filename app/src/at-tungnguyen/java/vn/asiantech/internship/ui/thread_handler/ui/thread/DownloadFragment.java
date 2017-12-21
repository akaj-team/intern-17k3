package vn.asiantech.internship.ui.thread_handler.ui.thread;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private int mStatus = 0;
    private ImageView mImgBitmap;
    public Bitmap mBitmap;
    private Handler mProgressBarHandler = new Handler();
    private String mImageUrl = "https://i.ytimg.com/vi/Orh592OBoKY/maxresdefault.jpg";

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
        initDownloadImage();
        return mView;
    }

    private void initView() {
        mImgBitmap = mView.findViewById(R.id.imgBitmap);
        tvPercent = mView.findViewById(R.id.tvPercentage);
        mProgressBar = mView.findViewById(R.id.progressBar);
        mBtnStart = mView.findViewById(R.id.btnStart);
    }

    private void initData() {
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
    }

    private void initListener() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBitmapFromURL(mImageUrl);
                initDownloadImage();
            }
        });
    }

    private void initDownloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mStatus < 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgressBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mImgBitmap.setImageBitmap(mBitmap);
                            mProgressBar.setProgress(mStatus);
                            tvPercent.setText(mStatus + "%");
                        }
                    });
                }
                if (mStatus >= 100) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mStatus = 0;
                }
            }
        }).start();
    }

    public void getBitmapFromURL(final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    int fileLength = connection.getContentLength();
                    InputStream input = connection.getInputStream();
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte data[] = new byte[512];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                        mStatus = (int) (total * 100 / fileLength);
                        mBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.size());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(mBitmap!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ThreadActivity)getActivity()).setBitMap(mBitmap);
                        }
                    });
                }

            }
        }).start();
    }

}
