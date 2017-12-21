package vn.asiantech.internship.ui.asynchronous.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.CountDownTimerItem;
import vn.asiantech.internship.ui.asynchronous.adapters.CountDownTimerAdapter;

/**
 * Class CountDownTimerActivity
 */
public class CountDownTimerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CountDownTimerAdapter mCountDownTimerAdapter;
    private List<CountDownTimerItem> mCountDownTimers = new ArrayList<>();
    private TextView mTextView;
    private int mNumberItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initViews();
        initData();
        initAdapter();

        /*
         * Countdown seconds from 180->0
         */
        new CountDownTimer(180000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                mTextView.setText(getString(R.string.seconds_remaining) + 180);
                mTextView.setText(getString(R.string.seconds_remaining) + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                mTextView.setText(R.string.done);
            }
        }.start();

        /*
         * CountDown 10s add 2 item
         */
        new CountDownTimer(180000, 10000) {
            @Override
            public void onTick(long l) {
                mCountDownTimers.add(new CountDownTimerItem("item " + (mNumberItem + 1)));
                mCountDownTimers.add(new CountDownTimerItem("item " + (mNumberItem + 2)));
                mCountDownTimerAdapter.notifyDataSetChanged();
                mNumberItem = mNumberItem + 2;
                Log.d("l", "list: " + mCountDownTimers.size());
            }

            @Override
            public void onFinish() {
                // No-op
            }
        }.start();

        /*
          CountDown 15s remove 1 item in the middle of the listItem
         */
        new CountDownTimer(180000, 15000) {
            @Override
            public void onTick(long l) {
                mCountDownTimers.remove(mCountDownTimers.get(mCountDownTimers.size() / 2));
                mCountDownTimerAdapter.notifyDataSetChanged();
                Log.d("l", "removed item: " + (mCountDownTimers.size() / 2));
                Log.d("l", "list 2: " + mCountDownTimers.size());
            }

            @Override
            public void onFinish() {
                // No-op
            }
        }.start();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDownTimer);
        mTextView = findViewById(R.id.tvA);
    }

    private void initData() {
        mCountDownTimers.add(new CountDownTimerItem("item 1"));
        mCountDownTimers.add(new CountDownTimerItem("item 2"));
        mCountDownTimers.add(new CountDownTimerItem("item 3"));
        mNumberItem = mCountDownTimers.size() - 1;
    }

    private void initAdapter() {
        mCountDownTimerAdapter = new CountDownTimerAdapter(mCountDownTimers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownTimerAdapter);
    }
}
