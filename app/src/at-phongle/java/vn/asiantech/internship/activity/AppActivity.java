package vn.asiantech.internship.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.calculator.CalculatorActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.PostActivity;
import vn.asiantech.internship.savedata.ExternalStorageActivity;
import vn.asiantech.internship.savedata.PersonActivity;
import vn.asiantech.internship.savedata.SharePreferenceActivity;
import vn.asiantech.internship.viewpager.GuideActivity;

public class AppActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculatorScreen;
    private Button mBtnRecyclerViewScreen;
    private Button mBtnDrawerLayoutScreen;
    private Button mSharePreferenceScreen;
    private Button mExternalStorageScreen;
    private Button mSQLiteScreen;
    private Button mBtnViewPager;

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
        mSharePreferenceScreen = findViewById(R.id.btnSharePreferenceScreen);
        mExternalStorageScreen = findViewById(R.id.btnExternalStorageScreen);
        mSQLiteScreen = findViewById(R.id.btnSQLiteScreen);
        mBtnViewPager = findViewById(R.id.btnViewPager);
    }

    private void addListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculatorScreen.setOnClickListener(this);
        mBtnDrawerLayoutScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mSharePreferenceScreen.setOnClickListener(this);
        mExternalStorageScreen.setOnClickListener(this);
        mSQLiteScreen.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
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
//            case R.id.btnDrawerLayoutScreen:
//                intent = new Intent(AppActivity.this, DrawerLayoutActivity.class);
//                startActivity(intent);
//                break;
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
            case R.id.btnViewPager:
                intent = new Intent(AppActivity.this, GuideActivity.class);
                startActivity(intent);
                break;
        }
    }
}
