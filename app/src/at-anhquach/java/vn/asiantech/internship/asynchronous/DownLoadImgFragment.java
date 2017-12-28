package vn.asiantech.internship.asynchronous;

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
 * Created by anh.quach on 12/19/17.
 * Render 1 button, 1 ProgressBar
 */
public class DownLoadImgFragment extends Fragment {
    private Button mBtnClick;
    private int mTotalSizeImg;
    private int mDownloadedSizeImg = 0;
    private TextView mTvResutlDownLoadImg;
    private ProgressBar mProgressBarDownLoadImg;
    private Bitmap myBitmap;

    public DownLoadImgFragment newInstance() {
        return new DownLoadImgFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download_image, container, false);
        initViews(view);
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBarDownLoadImg.setProgress(0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloadPhoto();
                    }
                }).start();
                mBtnClick.setEnabled(false);
            }
        });
        return view;
    }

    private void initViews(View view) {
        mBtnClick = view.findViewById(R.id.btnDownLoadImg);
        mProgressBarDownLoadImg = view.findViewById(R.id.progressBarDownLoadImg);
        mTvResutlDownLoadImg = view.findViewById(R.id.tvDownLoadImg);
    }

    private void downloadPhoto() {
        try {
            URL url = new URL("https://wallpaperbrowse.com/media/images/dd170f37d84b3b21635f2ece8d416afa_large.jpeg");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            mTotalSizeImg = connection.getContentLength();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBarDownLoadImg.setMax(mTotalSizeImg);
                }
            });

            int bufferLength;
            while ( -1 != (bufferLength = input.read())) {
                output.write(bufferLength);
                myBitmap = BitmapFactory.decodeByteArray(output.toByteArray(), 0, output.toByteArray().length);
                mDownloadedSizeImg += bufferLength;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBarDownLoadImg.setProgress(mDownloadedSizeImg);
                        int per = (int) (((float) mDownloadedSizeImg / (float) mTotalSizeImg) * 100);
                        mTvResutlDownLoadImg.setText(getString(R.string.textview_download, mDownloadedSizeImg, mTotalSizeImg, per));
                    }
                });
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ViewImgFragment) ((ThreadActivity) getActivity()).mTabLayoutAdapter.getItem(1)).showPhoto(myBitmap);
                }
            });
        } catch (IOException downloadImg) {
            Log.d(getResources().getString(R.string.tag_error), downloadImg.getMessage());
        }
    }
}
