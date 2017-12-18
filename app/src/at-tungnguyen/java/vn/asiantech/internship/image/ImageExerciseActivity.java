package vn.asiantech.internship.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Select type image
 */
public class ImageExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadImage;
    private Button mBtnLoadNinePath;
    private Button mBtnLoadVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_exercise);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnLoadNinePath = findViewById(R.id.btnLoadNinePath);
        mBtnLoadVector = findViewById(R.id.btnLoadVector);
    }

    private void initListener() {
        mBtnLoadImage.setOnClickListener(this);
        mBtnLoadNinePath.setOnClickListener(this);
        mBtnLoadVector.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadImage:
                Intent intentLoadImage = new Intent(this, LoadImageActivity.class);
                startActivity(intentLoadImage);
                break;
            case R.id.btnLoadNinePath:
                Intent intentNinePath = new Intent(this, NinePathActivity.class);
                startActivity(intentNinePath);
                break;
            case R.id.btnLoadVector:
                Intent intentVector = new Intent(this, VectorActivity.class);
                startActivity(intentVector);
                break;
        }
    }
}
