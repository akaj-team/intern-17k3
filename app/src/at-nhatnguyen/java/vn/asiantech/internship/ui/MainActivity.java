package vn.asiantech.internship.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.recyclerview.CommentActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtnRecyclerView;
    private Button mBtnCalculator;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private void initListener() {
        mBtnRecyclerView.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnRecyclerView:
                startActivity(new Intent(this, CommentActivity.class));
                break;
            case R.id.btnCalculator:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
        }
    }
}
