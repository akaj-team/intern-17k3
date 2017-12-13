package vn.asiantech.internship.ui.imageview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * This class use for controller activity
 */
public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadImage;
    private Button mBtnScaleEditText;
    private Button mBtnImageVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnScaleEditText = findViewById(R.id.btnScaleEditText);
        mBtnImageVector = findViewById(R.id.btnImageVector);
    }

    private void initListener() {
        mBtnLoadImage.setOnClickListener(this);
        mBtnScaleEditText.setOnClickListener(this);
        mBtnImageVector.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadImage:
                startActivity(new Intent(this, LoadImageActivity.class));
                break;
            case R.id.btnScaleEditText:
                startActivity(new Intent(this, ScaleImageActivity.class));
                break;
            case R.id.btnImageVector:
                startActivity(new Intent(this, VectorImageActivity.class));
                break;
        }
    }
}
