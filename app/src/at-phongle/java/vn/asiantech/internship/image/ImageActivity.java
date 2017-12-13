package vn.asiantech.internship.image;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Image;

/**
 * Created by phongle on 12/12/2560.
 * ImageActivity
 */

public class ImageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewImage;
    private List<Image> mImageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewImage = findViewById(R.id.recyclerViewImage);
    }

    private void initData() {
        mImageList = new ArrayList<>();
        mImageList.add(new Image("https://bleumag.com/v2/wp-content/uploads/2017/06/Cristiano-Ronaldo-Hairstyle-2017-Perspective-2.jpg", 0, "Load image from Internet"));
        mImageList.add(new Image("file:///android_asset/image/ronaldo.jpeg", 0, "Load image from Asset"));
        mImageList.add(new Image(null, R.drawable.ronaldo_mu, "Load image from Drawable"));
        mImageList.add(new Image(Uri.fromFile(new File(Environment
                .getExternalStorageDirectory().getPath() + "/Pictures/" + "cr7_mu_real.jpeg")).toString(), 0, "Load image from Storage"));
    }

    private void initAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(mImageList, this);
        mRecyclerViewImage.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewImage.setAdapter(imageAdapter);
    }
}
