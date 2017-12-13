package vn.asiantech.internship.ui.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.asiantech.internship.R;

public class LoadImageActivity extends AppCompatActivity {
    private ImageView mImgInternet;
    private ImageView mImgDrawable;
    private ImageView mImgAsset;
    private ImageView mImgStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        loadImage();
    }

    private void initViews() {
        mImgInternet = findViewById(R.id.imgInternet);
        mImgDrawable = findViewById(R.id.imgDrawable);
        mImgAsset = findViewById(R.id.imgAssets);
        mImgStorage = findViewById(R.id.imgStorage);
    }

    private void loadImage() {
        Picasso.with(this)
                .load("http://anh.24h.com.vn/upload/1-2017/images/2017-01-10/1484043345-148404276950210-internet-1.jpeg")
                .placeholder(R.drawable.ic_loading)
                .into(mImgInternet);
        Picasso.with(this)
                .load(R.drawable.ic_australia)
                .placeholder(R.drawable.ic_loading)
                .into(mImgDrawable);
        Picasso.with(this)
                .load("file:///android_asset/ic_logo_asset.png")
                .placeholder(R.drawable.ic_loading)
                .into(mImgAsset);
        Picasso.with(this)
                .load("file:///android_asset/ic_logo_asset.png")
                .placeholder(R.drawable.ic_loading)
                .into(mImgStorage);
    }
}
