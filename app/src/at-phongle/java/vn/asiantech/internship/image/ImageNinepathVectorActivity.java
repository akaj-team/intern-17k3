package vn.asiantech.internship.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 13/12/2560.
 * Image-Ninepath-Vector activity
 */
public class ImageNinepathVectorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadImage;
    private Button mBtnNinepath;
    private Button mBtnVector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_ninepath_vector);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnNinepath = findViewById(R.id.btnNinePath);
        mBtnVector = findViewById(R.id.btnVector);
    }

    private void addListener() {
        mBtnLoadImage.setOnClickListener(this);
        mBtnNinepath.setOnClickListener(this);
        mBtnVector.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnLoadImage:
                intent = new Intent(ImageNinepathVectorActivity.this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.btnNinePath:
                intent = new Intent(ImageNinepathVectorActivity.this, NinePathActivity.class);
                startActivity(intent);
                break;
            case R.id.btnVector:
                intent = new Intent(ImageNinepathVectorActivity.this, VectorActivity.class);
                startActivity(intent);
                break;
        }
    }
}
