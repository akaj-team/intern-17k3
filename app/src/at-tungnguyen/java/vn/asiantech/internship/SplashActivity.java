package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import vn.asiantech.internship.caculatorview.CalculatorActivity;
import vn.asiantech.internship.loginscreen.MainActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnLogin;
    private Button mbtnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    private void initViews() {
        mbtnLogin = findViewById(R.id.btnLogin);
        mbtnCalculator = findViewById(R.id.btnCaculator);
    }

    private void initListener() {
        mbtnLogin.setOnClickListener(this);
        mbtnCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnCaculator:
                Intent i2 = new Intent(SplashActivity.this, CalculatorActivity.class);
                startActivity(i2);
                break;
        }
    }
}
