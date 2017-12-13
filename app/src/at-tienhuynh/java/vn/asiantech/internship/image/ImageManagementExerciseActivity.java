package vn.asiantech.internship.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

public class ImageManagementExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoadImage;
    private Button mBtnNinePath;
    private Button mBtnDrawVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_management_exercise);
        initViews();
        initListeners();
    }

    /**
     * Init views
     */
    private void initViews() {
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnNinePath = findViewById(R.id.btnNinePath);
        mBtnDrawVector = findViewById(R.id.btnDrawVector);
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mBtnLoadImage.setOnClickListener(this);
        mBtnNinePath.setOnClickListener(this);
        mBtnDrawVector.setOnClickListener(this);
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadImage:
                startActivity(new Intent(ImageManagementExerciseActivity.this, LoadImageActivity.class));
                break;
            case R.id.btnNinePath:
                startActivity(new Intent(ImageManagementExerciseActivity.this, NinePathActivity.class));
                break;
            case R.id.btnDrawVector:
                startActivity(new Intent(ImageManagementExerciseActivity.this, DrawVectorActivity.class));
                break;
        }

    }
}
