package vn.asiantech.internship.ui.loadimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 12/12/2017.
 * LoadImageActivity: load image use picasso
 */
public class LoadImageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgPicasso;
    private Button mBtnDrawable;
    private Button mBtnAssets;
    private Button mBtnStorage;
    private Button mBtnInternet;
    private Button mBtnNinePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        initListener();
    }

    private void initViews() {
        mImgPicasso = findViewById(R.id.imgPicasso);
        mBtnDrawable = findViewById(R.id.btnDrawable);
        mBtnAssets = findViewById(R.id.btnAssets);
        mBtnStorage = findViewById(R.id.btnStorage);
        mBtnInternet = findViewById(R.id.btnInternet);
        mBtnNinePath = findViewById(R.id.btnNinePath);
    }

    private void initListener() {
        mBtnDrawable.setOnClickListener(this);
        mBtnAssets.setOnClickListener(this);
        mBtnStorage.setOnClickListener(this);
        mBtnInternet.setOnClickListener(this);
        mBtnNinePath.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDrawable:
                Picasso.with(this)
                        .load(R.drawable.ic_flag)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImgPicasso);
                break;
            case R.id.btnAssets:
                Picasso.with(this)
                        .load("file:///android_asset/bg_desktop.png")
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImgPicasso);
                break;
            case R.id.btnStorage:
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/"));
                String imageName = "apple_mac_os_x_el_capitan.jpg";
                Picasso.with(LoadImageActivity.this)
                        .load(uri + imageName)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImgPicasso);
                break;
            case R.id.btnInternet:
                Picasso.with(LoadImageActivity.this)
                        .load("https://goo.gl/u9wuW9")
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImgPicasso);
                break;
            case R.id.btnNinePath:
                startActivity(new Intent(this, NinePathActivity.class));
                break;
        }
    }
}
