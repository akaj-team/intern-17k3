package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnIssues1;
    private Button mBtnIssues2;
    private Button mBtnIssues3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnIssues1 = findViewById(R.id.btnIssuses1);
        mBtnIssues2 = findViewById(R.id.btnIssuses2);
        mBtnIssues3 = findViewById(R.id.btnIssuses3);
    }

    private void addListener() {
        mBtnIssues1.setOnClickListener(this);
        mBtnIssues2.setOnClickListener(this);
        mBtnIssues3.setOnClickListener(this);
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
        }
    }
}
