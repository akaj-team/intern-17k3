package vn.asiantech.internship.ui.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * This activity use for manager
 */
public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnThread;
    private Button mBtnCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnThread = findViewById(R.id.btnThreadDown);
        mBtnCountDown = findViewById(R.id.btnCountDownTimer);
    }

    private void initListener() {
        mBtnThread.setOnClickListener(this);
        mBtnCountDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThreadDown:
                startActivity(new Intent(this, LoadImageActivity.class));
                break;
            case R.id.btnCountDownTimer:
                startActivity(new Intent(this, CountDownActivity.class));
                break;
        }
    }
}
