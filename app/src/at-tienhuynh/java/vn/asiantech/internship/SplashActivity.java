package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.calculation.CaculatorActivity;
import vn.asiantech.internship.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculationScreen;

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
    }

    private void initListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculationScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginScreen:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnCalculatorScreen:
                startActivity(new Intent(this, CaculatorActivity.class));
                break;
        }
    }
}
