package vn.asiantech.internship.ui.thread_handler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread_handler.ui.countdowntimer.CountDownActivity;
import vn.asiantech.internship.ui.thread_handler.ui.thread.ThreadActivity;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class SplashThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnExercise1;
    private Button mBtnExercise2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_thread);
        initView();
        initListener();
    }

    private void initView() {
        mBtnExercise1 = findViewById(R.id.btnExThread1);
        mBtnExercise2 = findViewById(R.id.btnExThread2);
    }

    private void initListener() {
        mBtnExercise1.setOnClickListener(this);
        mBtnExercise2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExThread1:
                Intent intentThread = new Intent(this, ThreadActivity.class);
                startActivity(intentThread);
                break;
            case R.id.btnExThread2:
                Intent intentCountDown = new Intent(this, CountDownActivity.class);
                startActivity(intentCountDown);
        }
    }
}
