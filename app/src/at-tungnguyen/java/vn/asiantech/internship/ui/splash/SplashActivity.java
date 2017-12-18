package vn.asiantech.internship.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.image.ImageExerciseActivity;
import vn.asiantech.internship.ui.caculatorview.CalculatorActivity;
import vn.asiantech.internship.ui.drawer.DrawerActivity;
import vn.asiantech.internship.ui.login.MainActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnCalculator;
    private Button mBtnRecyclerView;
    private Button mBtnLoadImage;
    private Button mBtnDrawerlayout;

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
        mBtnDrawerlayout = findViewById(R.id.btnDrawerlayout);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnDrawerlayout.setOnClickListener(this);
        mBtnLoadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent intentLogin = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.btnCalculator:
                Intent intentCalculator = new Intent(SplashActivity.this, CalculatorActivity.class);
                startActivity(intentCalculator);
                break;
            case R.id.btnRecyclerView:
                Intent intentRecyclerView = new Intent(SplashActivity.this, RecyclerViewActivity.class);
                startActivity(intentRecyclerView);
                break;
            case R.id.btnDrawerlayout:
                Intent intentDrawer = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(intentDrawer);
                break;
            case R.id.btnLoadImage:
                Intent intentLoadImage = new Intent(SplashActivity.this, ImageExerciseActivity.class);
                startActivity(intentLoadImage);
                break;
        }
    }
}
