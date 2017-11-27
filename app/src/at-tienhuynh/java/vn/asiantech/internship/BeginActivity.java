package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnGoLogin;
    private Button mBtnGoCaculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        mBtnGoCaculator = findViewById(R.id.btnGoCaculator);
        mBtnGoLogin = findViewById(R.id.btnGoLogin);
        mBtnGoLogin.setOnClickListener(this);
        mBtnGoCaculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGoLogin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.btnGoCaculator:
                startActivity(new Intent(getApplicationContext(),CaculatorActivity.class));
                break;
        }
    }
}
