package vn.asiantech.internship.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.recyclerview.PersonViewActivity;
import vn.asiantech.internship.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_exercise);
        Button mBtnBt1 = findViewById(R.id.btnBt1);
        Button mBtnBt2 = findViewById(R.id.btnBt2);
        Button mBtnBt3 = findViewById(R.id.btnBt3);
        mBtnBt1.setOnClickListener(this);
        mBtnBt2.setOnClickListener(this);
        mBtnBt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBt1:
                Intent intent_bt1 =new Intent(this, LoginActivity.class);
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
        }
    }
}