package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private Handler handler;
    private String urlImage = "http://www.hdwallpaper.nu/wp-content/uploads/2016/01/zlatan_ibrahimovic_wallpaper_93603.jpg";

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
                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
//                        FirstFragment firstFragment = new FirstFragment();
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) msg.obj);
////
////                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
////                        byte[] byteArray = stream.toByteArray();
////                        Bundle bundle = new Bundle();
////                        bundle.putByteArray("image", byteArray);
////                        firstFragment.setArguments(bundle);
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mImg.setImageBitmap(bitmap);
//                            }
//                        });
                    }
                };

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        downloadFile();
                    }
                });
                thread.start();
            }
        });
    }

    void downloadFile() {

        try {
            URL url = new URL(urlImage);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            //connect
            urlConnection.connect();
            //Stream used for reading the data from the internet
            final InputStream inputStream = urlConnection.getInputStream();
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            final Message message = new Message();
            message.obj = inputStream;
//            handler.sendMessage(message);

            //this is the total size of the file which we are downloading
            totalSize = urlConnection.getContentLength();

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBar.setMax(totalSize);
                }
            });

            //create a buffer...
            final byte[] buffer = new byte[1024];
            int bufferLength;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
//                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                // update the progressbar //
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mProgressBar.setProgress(downloadedSize);
                        float per = ((float) downloadedSize / totalSize) * 100;
                        mTvPercentage.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int) per + "%)");
                    }
                });
                if (downloadedSize == totalSize) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            mImg.setImageBitmap(bitmap);
                        }
                    });
                    mBtnStart.setEnabled(false);
                }
            }

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
