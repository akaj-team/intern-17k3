package vn.asiantech.internship.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.image.ImageExerciseActivity;
import vn.asiantech.internship.ui.caculatorview.CalculatorActivity;
import vn.asiantech.internship.ui.datablind.DataBlindActivity;
import vn.asiantech.internship.ui.drawer.DrawerActivity;
import vn.asiantech.internship.ui.login.MainActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;
import vn.asiantech.internship.ui.unittest.LoginActivity;
import vn.asiantech.internship.ui.viewpager.ui.ViewPagerActivity;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnCalculator;
    private Button mBtnRecyclerView;
    private Button mBtnLoadImage;
    private Button mBtnDrawerlayout;
    private Button mBtnViewPager;
    private Button mBtnSaveData;
    private Button mBtnValidation;
    private Button mBtnDataBlind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    /**
     * initView SplashActivity
     */
    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnSaveData = findViewById(R.id.btnSaveData);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnDrawerlayout = findViewById(R.id.btnDrawerlayout);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnViewPager = findViewById(R.id.btnViewPager);
        mBtnValidation = findViewById(R.id.btnValidation);
        mBtnDataBlind = findViewById(R.id.btnDataBlind);
    }

    /**
     * initListener SplashActivity
     */
    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnDrawerlayout.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
        mBtnLoadImage.setOnClickListener(this);
        mBtnSaveData.setOnClickListener(this);
        mBtnValidation.setOnClickListener(this);
        mBtnDataBlind.setOnClickListener(this);
    }

    /**
     *
     * Onclick between Activity
     */
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
            case R.id.btnSaveData:
                Intent intentSaveData = new Intent(SplashActivity.this, SaveDataActivity.class);
                startActivity(intentSaveData);
                break;
            case R.id.btnDrawerlayout:
                Intent intentDrawer = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(intentDrawer);
                break;
            case R.id.btnLoadImage:
                Intent intentLoadImage = new Intent(SplashActivity.this, ImageExerciseActivity.class);
                startActivity(intentLoadImage);
                Intent intentDrawerLayout = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(intentDrawerLayout);
                break;
            case R.id.btnViewPager:
                Intent intentViewPager = new Intent(SplashActivity.this, ViewPagerActivity.class);
                startActivity(intentViewPager);
                break;
            case R.id.btnValidation:
                Intent intentValidation = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intentValidation);
                break;
            case R.id.btnDataBlind:
                Intent intentDatablind = new Intent(SplashActivity.this, DataBlindActivity.class);
                startActivity(intentDatablind);
        }
    }
}
