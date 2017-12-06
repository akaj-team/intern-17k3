package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.calculation.CalculatorActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;
import vn.asiantech.internship.savedata.SaveDataActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculationScreen;
    private Button mBtnRecyclerViewScreen;
    private Button mBtnSaveDataScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLoginScreen = findViewById(R.id.btnLoginScreen);
        mBtnCalculationScreen = findViewById(R.id.btnCalculatorScreen);
        mBtnRecyclerViewScreen = findViewById(R.id.btnRecyclerViewScreen);
        mBtnSaveDataScreen = findViewById(R.id.btnSaveDataScreen);
    }

    private void initListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculationScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mBtnSaveDataScreen.setOnClickListener(this);
    }

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
                startActivity(new Intent(this, SaveDataActivity.class));
                break;
        }
    }
}
