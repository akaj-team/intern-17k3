package vn.asiantech.internship.image;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import vn.asiantech.internship.R;

/**
 * Load image with Picasso library
 */
public class LoadImageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadDrawable;
    private Button mBtnLoadAsset;
    private Button mBtnLoadInternet;
    private Button mBtnStorage;
    private ImageView mImgLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLoadAsset = findViewById(R.id.btnLoadAsset);
        mBtnLoadDrawable = findViewById(R.id.btnLoadDrawable);
        mBtnLoadInternet = findViewById(R.id.btnLoadInternet);
        mBtnStorage = findViewById(R.id.btnLoadStorage);
        mImgLoadImage = findViewById(R.id.imgLoad);
    }

    private void initListener() {
        mBtnLoadAsset.setOnClickListener(this);
        mBtnLoadDrawable.setOnClickListener(this);
        mBtnLoadInternet.setOnClickListener(this);
        mBtnStorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadAsset:
                String imageAsset = "file:///android_asset/ic_messi.jpg";
                Picasso.with(this).load(imageAsset).into(mImgLoadImage);
                break;
            case R.id.btnLoadInternet:
                Picasso.with(this).load("https://www.thefamouspeople.com/profiles/images/fernando-torres-3.jpg").into(mImgLoadImage);
                break;
            case R.id.btnLoadDrawable:
                Picasso.with(this).load(R.drawable.ic_hazzad).into(mImgLoadImage);
                break;
            case R.id.btnLoadStorage:
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/", "ic_messi.jpg"));
                Picasso.with(this).load(uri).into(mImgLoadImage);
                break;
        }
    }
}
