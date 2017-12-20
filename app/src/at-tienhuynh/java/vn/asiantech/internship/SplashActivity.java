package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.calculation.CalculatorActivity;
import vn.asiantech.internship.drawerlayout.DrawerLayoutActivity;
import vn.asiantech.internship.image.ImageManagementExerciseActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;
import vn.asiantech.internship.savedata.ExerciseManagementActivity;
import vn.asiantech.internship.threadandhandler.SplashThreadAndHandlerActivity;
import vn.asiantech.internship.viewpagerandtablelayout.ui.SlideActivity;

/**
 * Created at 2017
 * Created by jackty on 19/12/2017.
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculationScreen;
    private Button mBtnRecyclerViewScreen;
    private Button mBtnSaveDataScreen;
    private Button mBtnDrawerLayoutScreen;
    private Button mBtnImageScreen;
    private Button mBtnViewPager;
    private Button mBtnThreadAndHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mBtnLoginScreen = findViewById(R.id.btnLoginScreen);
        mBtnCalculationScreen = findViewById(R.id.btnCalculatorScreen);
        mBtnRecyclerViewScreen = findViewById(R.id.btnRecyclerViewScreen);
        mBtnSaveDataScreen = findViewById(R.id.btnSaveDataScreen);
        mBtnRecyclerViewScreen = findViewById(R.id.btnRecyclerViewScreen);
        mBtnDrawerLayoutScreen = findViewById(R.id.btnDrawerLayout);
        mBtnImageScreen = findViewById(R.id.btnImageScreen);
        mBtnThreadAndHandler = findViewById(R.id.btnThreadAndHandleScreen);
        mBtnViewPager = findViewById(R.id.btnViewPager);
    }

    /**
     * Init Listener
     */
    private void initListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculationScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mBtnSaveDataScreen.setOnClickListener(this);
        mBtnDrawerLayoutScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mBtnImageScreen.setOnClickListener(this);
        mBtnThreadAndHandler.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginScreen:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnCalculatorScreen:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.btnRecyclerViewScreen:
                startActivity(new Intent(this, StatusActivity.class));
                break;
            case R.id.btnSaveDataScreen:
                startActivity(new Intent(this, ExerciseManagementActivity.class));
                break;
            case R.id.btnDrawerLayout:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
            case R.id.btnImageScreen:
                startActivity(new Intent(this, ImageManagementExerciseActivity.class));
                break;
            case R.id.btnThreadAndHandleScreen:
                startActivity(new Intent(this, SplashThreadAndHandlerActivity.class));
                break;
            case R.id.btnViewPager:
                startActivity(new Intent(this, SlideActivity.class));
        }
    }
}
