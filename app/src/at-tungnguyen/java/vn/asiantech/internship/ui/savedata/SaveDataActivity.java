package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex1.SharedPreferencesActivity;
import vn.asiantech.internship.ui.savedata.ex2.ExternalActivity;
import vn.asiantech.internship.ui.savedata.ex3.UserActivity;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class SaveDataActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnExercise1;
    private Button mBtnExercise2;
    private Button mBtnExercise3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnExercise1 = findViewById(R.id.btnExercise1);
        mBtnExercise2 = findViewById(R.id.btnExercise2);
        mBtnExercise3 = findViewById(R.id.btnExercise3);
    }

    private void initListener() {
        mBtnExercise1.setOnClickListener(this);
        mBtnExercise2.setOnClickListener(this);
        mBtnExercise3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExercise1:
                Intent intent1 = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnExercise2:
                Intent intent2 = new Intent(this, ExternalActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnExercise3:
                Intent intent3 = new Intent(this, UserActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
