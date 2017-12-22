package vn.asiantech.internship.ui.thread_handler_countdowntmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread_handler_countdowntmer.count_down_timer.FootballTeamActivity;
import vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler.DownloadActivity;

/**
 * Created by tiboo on 21/12/2017.
 * Create Main activity week 5
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnThreadHandler;
    private Button mBtnCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersice_week_five);
        initViews();
        addListener();
    }

    private void addListener() {
        mBtnThreadHandler.setOnClickListener(this);
        mBtnCountDownTimer.setOnClickListener(this);
    }

    private void initViews() {
        mBtnThreadHandler = findViewById(R.id.btnThreadHandler);
        mBtnCountDownTimer = findViewById(R.id.btnCountDownTimer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThreadHandler:
                Intent intentThread = new Intent(this, DownloadActivity.class);
                this.startActivity(intentThread);
                break;
            case R.id.btnCountDownTimer:
                Intent intentCountDownTimer = new Intent(this, FootballTeamActivity.class);
                this.startActivity(intentCountDownTimer);
        }
    }
}
