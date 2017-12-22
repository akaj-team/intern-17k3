package vn.asiantech.internship.ui.thread.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread.LoadImageActivity;

/**
 * A simple {@link Fragment} subclass.
 * This fragment use to download
 */
public class OneFragment extends Fragment implements View.OnClickListener {
    private static final String LINK_PICTURE = "http://file.vforum.vn/hinh/2016/01/hinh-nen-phong-canh-4k-3.jpg";
    private Button mBtnDownLoadImage;
    private Bitmap mBitmap;
    private ProgressBar mProgressBar;
    private int mPercent;
    private Handler handler = new Handler();

    @NonNull
    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initViews(view);
        initListener();
        return view;
    }

    private void initViews(View view) {
        mBtnDownLoadImage = view.findViewById(R.id.btnDownloadImage);
        mProgressBar = view.findViewById(R.id.progressBar);
    }

    private void initListener() {
        mBtnDownLoadImage.setOnClickListener(this);
    }

    private void downloadPhoto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try {
                    Thread.sleep(1000);
                    connection = (HttpURLConnection) new URL(LINK_PICTURE).openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    int fileLength = connection.getContentLength();
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte data[] = new byte[128];
                    long total = 0;
                    int count;
                    while ((count = inputStream.read(data)) != -1) {
                        total += count;
                        mPercent = (int) (total * 100 / fileLength);
                        outputStream.write(data, 0, count);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(mPercent);
                            }
                        });
                        mBitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length);
                    }
                } catch (IOException e) {
                    Log.d("", e.getMessage());
                } catch (InterruptedException e) {
                    Log.d("", e.getMessage());
                } finally {
                    try {
                        if (connection != null) {
                            connection.disconnect();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((LoadImageActivity) getActivity()).getBitmap(mBitmap);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDownloadImage) {
            downloadPhoto();
        }
    }
}
