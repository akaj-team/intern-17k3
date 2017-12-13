package vn.asiantech.internship.loadImage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.asiantech.internship.R;

public class LoadImageActivity extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView mImgContainImage;
    private Button mBtnChoiceDrawable;
    private Button mBtnChoiceAssets;
    private Button mBtnChoiceStorage;
    private Button mBtnChoiceInternet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        addListener();
    }

    private void initViews() {
        mImgContainImage = findViewById(R.id.imgContainImage);
        mBtnChoiceDrawable = findViewById(R.id.btnDrawable);
        mBtnChoiceAssets = findViewById(R.id.btnAsset);
        mBtnChoiceStorage = findViewById(R.id.btnStorage);
        mBtnChoiceInternet = findViewById(R.id.btnInternet);
    }

    private void addListener() {
        mBtnChoiceDrawable.setOnClickListener(this);
        mBtnChoiceAssets.setOnClickListener(this);
        mBtnChoiceStorage.setOnClickListener(this);
        mBtnChoiceInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDrawable:
                Picasso.with(this).load(R.drawable.ic_pogba).placeholder(R.mipmap.ic_launcher).into(mImgContainImage);
                break;
            case R.id.btnAsset:
                Picasso.with(view.getContext()).load("file:///android_asset/img_ibrahimovich.jpg").into(mImgContainImage);
                break;
            case R.id.btnStorage:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.btnInternet:
                Picasso.with(view.getContext()).load("https://i2-prod.manchestereveningnews.co.uk/incoming/article1736623.ece/ALTERNATES/s1227b/Paul%20Scholes.jpg").into(mImgContainImage);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImageURI = data.getData();
            Picasso.with(LoadImageActivity.this).load(selectedImageURI).noPlaceholder().centerCrop().fit().into(mImgContainImage);
        }
    }
}
