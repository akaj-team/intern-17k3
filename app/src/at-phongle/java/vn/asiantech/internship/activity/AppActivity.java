package vn.asiantech.internship.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.asynchronous.CountdownTimerActivity;
import vn.asiantech.internship.asynchronous.HandlerThreadActivity;
import vn.asiantech.internship.calculator.CalculatorActivity;
import vn.asiantech.internship.drawerlayout.DrawerLayoutActivity;
import vn.asiantech.internship.image.ImageNinepathVectorActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.PostActivity;
import vn.asiantech.internship.savedata.ExternalStorageActivity;
import vn.asiantech.internship.savedata.PersonActivity;
import vn.asiantech.internship.savedata.SharePreferenceActivity;

public class AppActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculatorScreen;
    private Button mBtnRecyclerViewScreen;
    private Button mBtnDrawerLayoutScreen;
    private Button mBtnSharePreferenceScreen;
    private Button mBtnExternalStorageScreen;
    private Button mBtnSQLiteScreen;
    private Button mBtnImageNinepathVector;
    private Button mBtnThreadHandler;
    private Button mBtnCountdown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnLoginScreen = findViewById(R.id.btnLoginScreen);
        mBtnCalculatorScreen = findViewById(R.id.btnCalculatorScreen);
        mBtnDrawerLayoutScreen = findViewById(R.id.btnDrawerLayoutScreen);
        mBtnRecyclerViewScreen = findViewById(R.id.btnRecyclerViewScreen);
        mBtnSharePreferenceScreen = findViewById(R.id.btnSharePreferenceScreen);
        mBtnExternalStorageScreen = findViewById(R.id.btnExternalStorageScreen);
        mBtnSQLiteScreen = findViewById(R.id.btnSQLiteScreen);
        mBtnImageNinepathVector = findViewById(R.id.btnImageNinepathVector);
        mBtnThreadHandler = findViewById(R.id.btnThreadHandler);
        mBtnCountdown = findViewById(R.id.btnCountdownTimer);
    }

    private void addListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculatorScreen.setOnClickListener(this);
        mBtnDrawerLayoutScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mBtnSharePreferenceScreen.setOnClickListener(this);
        mBtnExternalStorageScreen.setOnClickListener(this);
        mBtnSQLiteScreen.setOnClickListener(this);
        mBtnImageNinepathVector.setOnClickListener(this);
        mBtnThreadHandler.setOnClickListener(this);
        mBtnCountdown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnCalculatorScreen:
                intent = new Intent(AppActivity.this, CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRecyclerViewScreen:
                intent = new Intent(AppActivity.this, PostActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLoginScreen:
                intent = new Intent(AppActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDrawerLayoutScreen:
                intent = new Intent(AppActivity.this, DrawerLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSharePreferenceScreen:
                intent = new Intent(AppActivity.this, SharePreferenceActivity.class);
                startActivity(intent);
                break;
            case R.id.btnExternalStorageScreen:
                intent = new Intent(AppActivity.this, ExternalStorageActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSQLiteScreen:
                intent = new Intent(AppActivity.this, PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.btnImageNinepathVector:
                intent = new Intent(AppActivity.this, ImageNinepathVectorActivity.class);
                startActivity(intent);
                break;
            case R.id.btnThreadHandler:
                intent = new Intent(AppActivity.this, HandlerThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCountdownTimer:
                intent = new Intent(AppActivity.this, CountdownTimerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
