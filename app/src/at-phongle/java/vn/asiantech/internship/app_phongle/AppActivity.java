package vn.asiantech.internship.app_phongle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.app_calculator.CalculatorActivity;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.PostActivity;

public class AppActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLoginScreen;
    private Button mBtnCalculatorScreen;

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
    }

    private void addListener() {
        mBtnLoginScreen.setOnClickListener(this);
        mBtnCalculatorScreen.setOnClickListener(this);
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
//                intent = new Intent(AppActivity.this, LoginActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
