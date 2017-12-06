package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.calculation.CalculatorActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.StatusActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculationScreen;
    private Button mBtnRecyclerViewScreen;

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
    }

    private void initListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculationScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
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
        }
    }
}
