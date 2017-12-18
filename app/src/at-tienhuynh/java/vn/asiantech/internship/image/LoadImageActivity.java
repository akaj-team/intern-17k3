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
 * Created at 2017
 * Created by jackty on 30/11/2017.
 */
public class LoadImageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadFromDrawable;
    private Button mBtnLoadFromAsset;
    private Button mBtnLoadFromStorage;
    private Button mBtnLoadFromInternet;
    private ImageView mImgLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        initListeners();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mBtnLoadFromDrawable = findViewById(R.id.btnLoadFromDrawable);
        mBtnLoadFromAsset = findViewById(R.id.btnLoadFromAsset);
        mBtnLoadFromStorage = findViewById(R.id.btnLoadFromStorage);
        mBtnLoadFromInternet = findViewById(R.id.btnLoadFromInternet);
        mImgLoad = findViewById(R.id.imgLoad);
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mBtnLoadFromDrawable.setOnClickListener(this);
        mBtnLoadFromAsset.setOnClickListener(this);
        mBtnLoadFromStorage.setOnClickListener(this);
        mBtnLoadFromInternet.setOnClickListener(this);
    }

    /**
     * OnClick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadFromDrawable:
                Picasso.with(view.getContext())
                        .load(R.drawable.ic_logo_load)
                        .into(mImgLoad);
                break;
            case R.id.btnLoadFromAsset:
                Picasso.with(view.getContext())
                        .load("file:///android_asset/images/ic_logo_load.jpg")
                        .into(mImgLoad);
                break;
            case R.id.btnLoadFromStorage:
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()
                        + "/Pictures/", "ic_logo_load_storage.png"));
                Picasso.with(view.getContext())
                        .load(uri)
                        .into(mImgLoad);
                break;
            case R.id.btnLoadFromInternet:
                String url = "https://codelabs.developers.google.com/codelabs/fire-place/img/img-2.png";
                Picasso.with(view.getContext())
                        .load(url)
                        .into(mImgLoad);
                break;
        }
    }
}
