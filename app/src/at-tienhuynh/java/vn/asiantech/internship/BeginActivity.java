package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBanIssues1;
    private Button mBanIssues2;
    private Button mBanIssues3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        initViews();
        addListener();
    }

    private void initViews() {
        mBanIssues1 = findViewById(R.id.btnIssuses1);
        mBanIssues2 = findViewById(R.id.btnIssuses2);
        mBanIssues3 = findViewById(R.id.btnIssuses3);
    }

    private void addListener() {
        mBanIssues1.setOnClickListener(this);
        mBanIssues2.setOnClickListener(this);
        mBanIssues3.setOnClickListener(this);
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
