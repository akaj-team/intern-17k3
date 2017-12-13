package vn.asiantech.internship.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.imageview.LoadImageActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.recyclerview.CommentActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtnRecyclerView;
    private Button mBtnCalculator;
    private Button mBtnLogin;
    private Button mBtnSaveData;
    private Button mBtnLoadImage;

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
        mBtnSaveData = findViewById(R.id.btnSaveData);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
    }

    private void initListener() {
        mBtnRecyclerView.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnSaveData.setOnClickListener(this);
        mBtnLoadImage.setOnClickListener(this);
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
            case R.id.btnSaveData:
                startActivity(new Intent(this, SaveDataActivity.class));
                break;
            case R.id.btnLoadImage:
                startActivity(new Intent(this, LoadImageActivity.class));
                break;
        }
    }
}