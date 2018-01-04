package vn.asiantech.internship.ui.asynchronous.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Class AsynchronousActivity
 */
public class AsynchronousActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnThread;
    private Button mBtnCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynchronous);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnThread = findViewById(R.id.btnThread);
        mBtnCountDownTimer = findViewById(R.id.btnCountDownTimer);
    }

    private void initListener() {
        mBtnThread.setOnClickListener(this);
        mBtnCountDownTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnThread:
                startActivity(new Intent(this, ThreadHandleActivity.class));
                break;
            case R.id.btnCountDownTimer:
                startActivity(new Intent(this, CountDownTimerActivity.class));
        }
    }
}
