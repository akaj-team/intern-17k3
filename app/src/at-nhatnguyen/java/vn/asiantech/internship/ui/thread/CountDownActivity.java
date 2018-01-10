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
    private static final int TOTAL_TIME = 180000;
    private static final int ONE_TIME = 1000;
    CountDownTimer mCountDownTimer;
    private CountDownAdapter mCountDownAdapter;
    private List<Integer> mNumber;
    private RecyclerView mRecycleViewCountDown;
    private TextView mTvTime;
    private int mCountSize = 0;
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
        mCountDownTimer = new CountDownTimer(TOTAL_TIME, ONE_TIME) {
            @Override
            public void onTick(long l) {
                mCount++;
                mTvTime.setText(String.valueOf(mCount));
                if (mCount % 10 == 0) {
                    mNumber.add(mCountSize);
                    mNumber.add(mCountSize + 1);
                    mCountDownAdapter.notifyDataSetChanged();
                    mCountSize += 2;
                }
                if (mCount % 15 == 0) {
                    mNumber.remove(mNumber.size() / 2);
                    mCountDownAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {
                //NO-OP
            }
        };

        mCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }
}
