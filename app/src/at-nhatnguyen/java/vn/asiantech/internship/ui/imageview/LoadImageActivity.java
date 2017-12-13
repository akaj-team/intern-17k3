package vn.asiantech.internship.ui.imageview;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import vn.asiantech.internship.R;

public class LoadImageActivity extends AppCompatActivity {
    private static final String FOLDER_PICTURE = "/picture";
    private static final String IMAGE_NAME = "/ic_btn_next_pressed.png";
    private static final String URL_IMAGE_INTERNET = "http://anh.24h.com.vn/upload/1-2017/images/2017-01-10/1484043345-148404276950210-internet-1.jpeg";
    private static final String PATH_ASSET = "file:///android_asset/ic_logo_asset.png";
    private ImageView mImgInternet;
    private ImageView mImgDrawable;
    private ImageView mImgAsset;
    private ImageView mImgStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        initFile();
        loadImage();
    }

    private void initViews() {
        mImgInternet = findViewById(R.id.imgInternet);
        mImgDrawable = findViewById(R.id.imgDrawable);
        mImgAsset = findViewById(R.id.imgAssets);
        mImgStorage = findViewById(R.id.imgStorage);
    }

    private void initFile() {
        File file = new File(Environment.getExternalStorageDirectory(), FOLDER_PICTURE);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
    }

    private void loadImage() {
        String path = Environment.getExternalStorageDirectory() + FOLDER_PICTURE + IMAGE_NAME;
        Picasso.with(this)
                .load(URL_IMAGE_INTERNET)
                .placeholder(R.drawable.ic_loading)
                .into(mImgInternet);
        Picasso.with(this)
                .load(R.drawable.ic_australia)
                .placeholder(R.drawable.ic_loading)
                .into(mImgDrawable);
        Picasso.with(this)
                .load(PATH_ASSET)
                .placeholder(R.drawable.ic_loading)
                .into(mImgAsset);
        Picasso.with(this)
                .load(new File(path))
                .placeholder(R.drawable.ic_loading)
                .into(mImgStorage);
    }
}
