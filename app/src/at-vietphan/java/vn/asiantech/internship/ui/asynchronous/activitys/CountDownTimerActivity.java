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
    private static final int MILLIS_IN_FUTURE = 180000;
    private static final int COUNTDOWN_INTERVAL_SECONDS = 1000;
    private static final int COUNTDOWN_INTERVAL_ADD_ITEMS = 10000;
    private static final int COUNTDOWN_INTERVAL_REMOVE_ITEMS = 15000;
    private RecyclerView mRecyclerView;
    private CountDownTimerAdapter mCountDownTimerAdapter;
    private List<CountDownTimerItem> mCountDownTimers = new ArrayList<>();
    private int mNumberItem;
    private TextView mTextView;

    /*
     * Countdown seconds from 180->0
     */
    CountDownTimer countDownSeconds = new CountDownTimer(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL_SECONDS) {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText(getString(R.string.seconds_remaining) + 180);
            int countSeconds = (int) (millisUntilFinished / 1000);
            mTextView.setText(getString(R.string.seconds_remaining) + countSeconds);
        }

        @Override
        public void onFinish() {
            mTextView.setText(R.string.done);
        }
    };

    /*
     * CountDown 10s add 2 item
     */
    CountDownTimer countDownAddItems = new CountDownTimer(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL_ADD_ITEMS) {
        @Override
        public void onTick(long l) {
            mCountDownTimers.add(new CountDownTimerItem("item " + mNumberItem));
            mCountDownTimers.add(new CountDownTimerItem("item " + (mNumberItem + 1)));
            mCountDownTimerAdapter.notifyDataSetChanged();
            mNumberItem = mNumberItem + 2;
            Log.d("l", "list: " + mCountDownTimers.size());
        }

        @Override
        public void onFinish() {
            // No-op
        }
    };

    /*
     * CountDown 15s remove 1 item in the middle of the listItem
     */
    CountDownTimer countDownRemoveItem = new CountDownTimer(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL_REMOVE_ITEMS) {
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initViews();
        initData();
        initAdapter();
        countDownSeconds.start();
        countDownAddItems.start();
        countDownRemoveItem.start();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDownTimer);
        mTextView = findViewById(R.id.tvA);
    }

    private void initData() {
        mCountDownTimers.add(new CountDownTimerItem("item 1"));
        mCountDownTimers.add(new CountDownTimerItem("item 2"));
        mCountDownTimers.add(new CountDownTimerItem("item 3"));
        mNumberItem = mCountDownTimers.size();
    }

    private void initAdapter() {
        mCountDownTimerAdapter = new CountDownTimerAdapter(mCountDownTimers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownTimerAdapter);
    }

    @Override
    protected void onDestroy() {
        countDownSeconds.cancel();
        countDownAddItems.cancel();
        countDownRemoveItem.cancel();
        super.onDestroy();
    }
}
