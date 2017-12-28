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
    private static final int TIMES_ADD_ITEMS = 10;
    private static final int TIMES_DELETE_ITEMS = 15;
    private RecyclerView mRecyclerView;
    private CountDownTimerAdapter mCountDownTimerAdapter;
    private List<CountDownTimerItem> mCountDownTimerItems = new ArrayList<>();
    private int mTotalItems;
    private TextView mTextView;
    private int mCountSeconds = 0;

    /*
     * Countdown seconds from 180->0, 10s add 2 item, 15s remove 1 item in between list
     */
    CountDownTimer mCountDownSeconds = new CountDownTimer(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL_SECONDS) {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText(getString(R.string.seconds_remaining) + 180);
            int countSeconds = (int) (millisUntilFinished / 1000);
            mTextView.setText(getString(R.string.seconds_remaining) + countSeconds);
            mCountSeconds++;
            if (mCountSeconds % TIMES_ADD_ITEMS == 0) {
                addItems();
            } else if (mCountSeconds % TIMES_DELETE_ITEMS == 0) {
                deleteItems();
            }
        }

        @Override
        public void onFinish() {
            mTextView.setText(R.string.done);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initViews();
        initData();
        initAdapter();
        mCountDownSeconds.start();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDownTimer);
        mTextView = findViewById(R.id.tvSeconds);
    }

    private void initData() {
        mCountDownTimerItems.add(new CountDownTimerItem("item 1"));
        mCountDownTimerItems.add(new CountDownTimerItem("item 2"));
        mCountDownTimerItems.add(new CountDownTimerItem("item 3"));
        mTotalItems = mCountDownTimerItems.size();
    }

    private void initAdapter() {
        mCountDownTimerAdapter = new CountDownTimerAdapter(mCountDownTimerItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownTimerAdapter);
    }

    private void addItems() {
        mCountDownTimerItems.add(new CountDownTimerItem("item " + (mTotalItems + 1)));
        mCountDownTimerItems.add(new CountDownTimerItem("item " + (mTotalItems + 2)));
        mCountDownTimerAdapter.notifyDataSetChanged();
        mTotalItems = mTotalItems + 2;
        Log.d("l", "list added: " + mCountDownTimerItems.size());
    }

    private void deleteItems() {
        mCountDownTimerItems.remove(mCountDownTimerItems.get(mCountDownTimerItems.size() / 2));
        mCountDownTimerAdapter.notifyDataSetChanged();
        Log.d("l", "removed item: " + (mCountDownTimerItems.size() / 2));
        Log.d("l", "list removed: " + mCountDownTimerItems.size());
    }

    @Override
    protected void onDestroy() {
        mCountDownSeconds.cancel();
        super.onDestroy();
    }
}
