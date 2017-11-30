package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculationScreen;
    private Button mBtnRecyclerViewScreen;
    private Button mBtnDrawerLayoutScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
    }

    private void initViews() {
        mBtnLoginScreen = findViewById(R.id.btnLoginScreen);
        mBtnCalculationScreen = findViewById(R.id.btnCalculatorScreen);
        mBtnRecyclerViewScreen = findViewById(R.id.btnRecyclerView);
        mBtnDrawerLayoutScreen = findViewById(R.id.btnDrawerLayout);
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculationScreen.setOnClickListener(this);
        mBtnRecyclerViewScreen.setOnClickListener(this);
        mBtnDrawerLayoutScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginScreen:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnCalculatorScreen:
                break;
            case R.id.btnRecyclerView:
                break;
            case R.id.btnDrawerLayout:
                break;
        }
    }
}
