package vn.asiantech.internship.ui.thread_handler.ui.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.CountDownItem;
import vn.asiantech.internship.ui.thread_handler.adapter.CountDownAdapter;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class CountDownActivity extends AppCompatActivity {
    private List<CountDownItem> mCountDownTimers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CountDownAdapter mCountDownAdapter;
    private TextView mTvCountDown;
    private CountDownTimer mCountDownTimer;
    private int mCount = 0;
    private static final int MILLISINFUTURE = 180000;
    private static final int COUNTDOWNINTERVAL = 1000;
    private int mAddItem = 0;
    private int mDeleteItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        initView();
        initAdapter();
        initData();
        initCountDown();

    }

    /**
     * initView
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDown);
        mTvCountDown = findViewById(R.id.tvCountDownTimer);
    }

    /**
     * initAdapter
     */
    private void initAdapter() {
        mCountDownAdapter = new CountDownAdapter(mCountDownTimers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownAdapter);
    }

    /**
     * initData for list mCountDownTimers
     */
    private void initData() {
        mCountDownTimers.add(new CountDownItem("Tung"));
        mCountDownTimers.add(new CountDownItem("Tung"));
        mCountDownTimers.add(new CountDownItem("Tung"));
    }

    /**
     * initCountDown
     */
    private void initCountDown() {
        mCountDownTimer = new CountDownTimer(MILLISINFUTURE, COUNTDOWNINTERVAL) {
            @Override
            public void onTick(long l) {
                mCount = (int) (l / 1000);
                mTvCountDown.setText(mCount+"");

                ++mAddItem;
                ++mDeleteItem;
                if (mAddItem == 10) {
                    mCountDownTimers.add(new CountDownItem("Tung"));
                    mCountDownTimers.add(new CountDownItem("Tung"));
                    mCountDownAdapter.notifyDataSetChanged();
                    mAddItem = 0;
                }
                if (mDeleteItem == 15) {
                    mCountDownTimers.remove(mCountDownTimers.size() / 2);
                    mCountDownAdapter.notifyDataSetChanged();
                    mDeleteItem = 0;
                }
            }

            @Override
            public void onFinish() {
                mCount = 0;
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }
}
