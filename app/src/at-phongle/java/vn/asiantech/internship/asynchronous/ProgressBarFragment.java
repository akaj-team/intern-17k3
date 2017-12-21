package vn.asiantech.internship.asynchronous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 19/12/2560.
 * Fragment ProgressBar Download image
 */

public class ProgressBarFragment extends Fragment {
    private static final String URL_IMAGE = "http://3.bp.blogspot.com/-TcpBN_zk_ak/VA5z32DetNI/AAAAAAAAECw/XNjuEcvEI2Q/s1600/hinh-anh-nen-manchester-united-2014-dep-nhat_19.jpg";
    private ProgressBar mProgressBarDownload;
    private TextView mTvProgressBar;
    private Button mBtnClickThread;
    private int mPhotoLength;
    private int mTotal = 0;

    public static Fragment getInstance() {
        return new ProgressBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_progress_bar, container, false);
        initViews(v);
        mProgressBarDownload.setMax(100);
        mBtnClickThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloadPhoto();
                    }
                }).start();
            }
        });
        return v;
    }

    private void initViews(View view) {
        mProgressBarDownload = view.findViewById(R.id.progressBarDownloadImage);
        mTvProgressBar = view.findViewById(R.id.tvProgressBar);
        mBtnClickThread = view.findViewById(R.id.btnClickThread);
    }

    private void downloadPhoto() {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            URL url = new URL(URL_IMAGE);
            connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            // Update progressBar
            mPhotoLength = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/downloadedfile.jpg");
            byte data[] = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1) {
                mTotal += count;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        float percent = (float) mTotal / (float) mPhotoLength * 100;
                        updateProgress((int) percent);
                    }
                });
                output.write(data, 0, count);
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
        if (bitmap != null) {
            ImageDownloadedFragment imageDownloadedFragment = new ImageDownloadedFragment();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Bundle b = new Bundle();
            b.putByteArray("image", byteArray);
            imageDownloadedFragment.setArguments(b);
            ((HandlerThreadActivity) getActivity()).replaceFragment(imageDownloadedFragment);
        }
    }

    private void updateProgress(int percent) {
        mProgressBarDownload.setProgress(percent);
        String text = String.valueOf(percent) + getResources().getString(R.string.tv_percen);
        mTvProgressBar.setText(text);
    }
}
