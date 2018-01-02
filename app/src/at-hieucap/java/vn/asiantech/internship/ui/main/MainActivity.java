package vn.asiantech.internship.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.PersonViewActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;

/**
 * Create Main activity
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnBt1;
    private Button mBtnBt2;
    private Button mBtnBt3;
    private Button mBtnBt4;
    private Button mBtnBt5;
    private Button mBtnBt6;
    private Button mBtnBt7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_exercise);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnBt1 = findViewById(R.id.btnBt1);
        mBtnBt2 = findViewById(R.id.btnBt2);
        mBtnBt3 = findViewById(R.id.btnBt3);
        mBtnBt4 = findViewById(R.id.btnBt4);
        mBtnBt5 = findViewById(R.id.btnBt5);
        mBtnBt6 = findViewById(R.id.btnBt6);
        mBtnBt7 = findViewById(R.id.btnBt7);
    }

    private void addListener() {
        mBtnBt1.setOnClickListener(this);
        mBtnBt2.setOnClickListener(this);
        mBtnBt3.setOnClickListener(this);
        mBtnBt4.setOnClickListener(this);
        mBtnBt5.setOnClickListener(this);
        mBtnBt6.setOnClickListener(this);
        mBtnBt7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBt1:
                Intent intent_bt1 = new Intent(this, LoginActivity.class);
                this.startActivity(intent_bt1);
                break;
            case R.id.btnBt2:
                Intent intent_bt2 = new Intent(this, CalculatorActivity.class);
                this.startActivity(intent_bt2);
                break;
            case R.id.btnBt3:
                Intent intent_bt3 = new Intent(this, PersonViewActivity.class);
                this.startActivity(intent_bt3);
                break;
            case R.id.btnBt4:
                Intent intent_bt4 = new Intent(this, vn.asiantech.internship.ui.loadimage.MainActivity.class);
                this.startActivity(intent_bt4);
                break;
            case R.id.btnBt5:
                Intent intent_bt5 = new Intent(this, SaveDataActivity.class);
                this.startActivity(intent_bt5);
                break;
            case R.id.btnBt6:
                Intent intent_bt6 = new Intent(this, vn.asiantech.internship.ui.drawerlayout.DrawerLayoutActivity.class);
                this.startActivity(intent_bt6);
                break;
            case R.id.btnBt7:
                Intent intent_bt7 = new Intent(this, vn.asiantech.internship.ui.viewpager.home.InitialActivity.class);
                this.startActivity(intent_bt7);
        }
    }
}
