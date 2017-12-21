package vn.asiantech.internship.ui.thread;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * This class countdown timer
 */
public class CountDownActivity extends AppCompatActivity {
    private CountDownAdapter mCountDownAdapter;
    private List<Integer> mNumber;
    private RecyclerView mRecycleViewCountDown;
    private TextView mTvTime;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        initViews();
        initAdapter();
        initRun();
    }

    private void initViews() {
        mTvTime = findViewById(R.id.tvTime);
        mRecycleViewCountDown = findViewById(R.id.recycleViewCountDown);
        mNumber = new ArrayList<>();
    }

    private void initAdapter() {
        mCountDownAdapter = new CountDownAdapter(mNumber);
        mRecycleViewCountDown.setLayoutManager(new LinearLayoutManager(this));
        mRecycleViewCountDown.setAdapter(mCountDownAdapter);
    }

    private void initRun() {
        CountDownTimer countDownTimer = new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long l) {
                mTvTime.setText(String.valueOf((180000 - l) / 1000));
            }

            @Override
            public void onFinish() {
                //NO-OP
            }
        };

        CountDownTimer countDownTimer1 = new CountDownTimer(180000, 10000) {

            @Override
            public void onTick(long l) {
                mNumber.add(mCount);
                mNumber.add(mCount + 1);
                mCountDownAdapter.notifyDataSetChanged();
                mCount += 2;
            }

            @Override
            public void onFinish() {
                //NO-OP
            }
        };

        CountDownTimer countDownTimer2 = new CountDownTimer(180000, 15000) {
            @Override
            public void onTick(long l) {
                mNumber.remove(mNumber.size() / 2);
                mCountDownAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {
                //NO-OP
            }
        };

        countDownTimer.start();
        countDownTimer1.start();
        countDownTimer2.start();
    }
}
