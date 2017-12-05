package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnIssuses1;
    private Button mBtnIssuses2;
    private Button mBtnIssuses3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnIssuses1 = findViewById(R.id.btnIssuses1);
        mBtnIssuses2 = findViewById(R.id.btnIssuses2);
        mBtnIssuses3 = findViewById(R.id.btnIssuses3);
    }

    private void addListener() {
        mBtnIssuses1.setOnClickListener(this);
        mBtnIssuses2.setOnClickListener(this);
        mBtnIssuses3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIssuses1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnIssuses2:
                break;
            case R.id.btnIssuses3:
                startActivity(new Intent(this, StatusActivity.class));
                break;
        }
    }
}
