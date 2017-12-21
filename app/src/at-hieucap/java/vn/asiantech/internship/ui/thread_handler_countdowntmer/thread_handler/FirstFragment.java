package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 20/12/2017.
 * Create first fragment
 */
public class FirstFragment extends Fragment {
    int downloadedSize = 0;
    int totalSize = 0;
    ImageView mImg;
    private ProgressBar mProgressBar;
    private Button mBtnStart;
    private TextView mTvPercentage;
    private Bitmap myBitmap;
    private String urlImage = "http://www.hdwallpaper.nu/wp-content/uploads/2016/01/zlatan_ibrahimovic_wallpaper_93603.jpg";

    public FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_w5, container, false);
        initViews(view);
        initData();
        addListener();
        return view;
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.progressBar);
        mTvPercentage = view.findViewById(R.id.tvPercentage);
        mBtnStart = view.findViewById(R.id.btnStart);
        mImg = view.findViewById(R.id.img);
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

    void downloadImage() {

        try {
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
                        float per = ((float) downloadedSize / totalSize) * 100;
                        mTvPercentage.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int) per + "%)");
                    }
                });
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((SecondFragment) ((DownloadActivity) getActivity()).mFragmentAdapter.getItem(1)).showPhoto(myBitmap);

                }
            });

        } catch (final MalformedURLException e) {
            showError("Error : MalformedURLException ");
            e.printStackTrace();
        } catch (final IOException e) {
            showError("Error : IOException ");
            e.printStackTrace();
        } catch (final Exception e) {
            showError("Error : Please check your internet connection ");
        }
    }

    void showError(final String err) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(FirstFragment.super.getActivity(), err, Toast.LENGTH_LONG).show();
            }
        });
    }
}
