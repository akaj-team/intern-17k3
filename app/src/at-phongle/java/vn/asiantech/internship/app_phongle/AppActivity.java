package vn.asiantech.internship.app_phongle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.app_calculator.CalculatorActivity;

public class AppActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Button btnLoginScreen = findViewById(R.id.btn_login_screen);
        Button btnCalculatorScreen = findViewById(R.id.btn_calculator_screen);
        btnCalculatorScreen.setOnClickListener(this);
        btnLoginScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_calculator_screen:
                intent = new Intent(AppActivity.this, CalculatorActivity.class);
                startActivity(intent);
                break;
//            case R.id.btn_login_screen:
//                intent = new Intent(AppActivity.this , LoginActivity.class);
//                break;
        }
    }
}
