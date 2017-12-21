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
 * Load image by picasso library
 */
public class LoadImageActivity extends AppCompatActivity {
    private ImageView mImgLoad;
    private Button mBtnLoadInDrawble;
    private Button mBtnLoadInAsset;
    private Button mBtnLoadInStorage;
    private Button mBtnLoadInInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);
        initViews();
        mBtnLoadInDrawble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(view.getContext())
                        .load(R.drawable.img_drawble)
                        .resize(1100, 1000)
                        .into(mImgLoad);
            }
        });
        mBtnLoadInAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(view.getContext())
                        .load("file:///android_asset/images/img_asset.jpeg")
                        .resize(1100, 1000)
                        .into(mImgLoad);
            }
        });
        mBtnLoadInStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory().getPath() + "/Pictures/" + "img_storage.jpeg"));
                Picasso.with(view.getContext())
                        .load(uri)
                        .resize(1100, 1000)
                        .into(mImgLoad);
            }
        });
        mBtnLoadInInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://static.pexels.com/photos/33109/fall-autumn-red-season.jpg";
                Picasso.with(view.getContext())
                        .load(url)
                        .resize(1100, 1000)
                        .into(mImgLoad);
            }
        });
    }

    private void initViews() {
        mImgLoad = findViewById(R.id.imgLoad);
        mBtnLoadInDrawble = findViewById(R.id.btnLoadInDrawble);
        mBtnLoadInAsset = findViewById(R.id.btnLoadInAsset);
        mBtnLoadInStorage = findViewById(R.id.btnLoadInStorage);
        mBtnLoadInInternet = findViewById(R.id.btnLoadInInternet);
    }
}
