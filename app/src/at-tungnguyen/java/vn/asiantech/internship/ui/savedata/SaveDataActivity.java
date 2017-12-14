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
    private Button mBtnEx1;
    private Button mBtnEx2;
    private Button mBtnEx3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnEx1 = findViewById(R.id.btnex1);
        mBtnEx2 = findViewById(R.id.btnex2);
        mBtnEx3 = findViewById(R.id.btnex3);
    }

    private void initListener() {
        mBtnEx1.setOnClickListener(this);
        mBtnEx2.setOnClickListener(this);
        mBtnEx3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnex1:
                Intent intent1 = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnex2:
                Intent intent2 = new Intent(this, ExternalActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnex3:
                Intent intent3 = new Intent(this, UserActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
