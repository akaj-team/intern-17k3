package vn.asiantech.internship.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.image.ImageExerciseActivity;
import vn.asiantech.internship.ui.caculatorview.CalculatorActivity;
import vn.asiantech.internship.ui.login.MainActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnCalculator;
    private Button mBtnRecyclerView;
    private Button mBtnLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnLoadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnCalculator:
                Intent i2 = new Intent(SplashActivity.this, CalculatorActivity.class);
                startActivity(i2);
                break;
            case R.id.btnRecyclerView:
                Intent i3 = new Intent(SplashActivity.this, RecyclerViewActivity.class);
                startActivity(i3);
                break;
            case R.id.btnLoadImage:
                Intent i5 = new Intent(SplashActivity.this, ImageExerciseActivity.class);
                startActivity(i5);
                break;

        }
    }
}
