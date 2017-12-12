package vn.asiantech.internship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.loadimage.LoadImageActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;

/**
 * Created by vietphan on 07/12/2017
 * ExerciseActivity
 */
public class ExerciseActivity extends Activity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnRecyclerView;
    private Button mBtnCalculator;
    private Button mBtnSaveData;
    private Button mBtnLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnSaveData = findViewById(R.id.btnSaveData);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
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
                startActivity(new Intent(this, RecyclerViewActivity.class));
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
