package vn.asiantech.internship.ui.loadimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 15/12/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadImage;
    private Button mBtnImageVector;
    private Button mBtnNinePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_load_image);
        initViews();
        addListener();
    }

    private void addListener() {
        mBtnLoadImage.setOnClickListener(this);
        mBtnImageVector.setOnClickListener(this);
        mBtnNinePath.setOnClickListener(this);
    }

    private void initViews() {
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnImageVector = findViewById(R.id.btnImageVector);
        mBtnNinePath = findViewById(R.id.btnNinePath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoadImage:
                Intent intent_load_image = new Intent(this, LoadImageActivity.class);
                this.startActivity(intent_load_image);
                break;
            case R.id.btnImageVector:
                Intent intent_image_vector = new Intent(this, ImageVectorActivity.class);
                this.startActivity(intent_image_vector);
                break;
            case R.id.btnNinePath:
                Intent intent_nine_path = new Intent(this, NinePathActivity.class);
                this.startActivity(intent_nine_path);
                break;
        }
    }
}
