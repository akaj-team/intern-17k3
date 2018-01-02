package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 21/12/2560.
 * Activity CountdownTimer recycler view
 */
public class CountdownTimerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewCountdown;
    private CountdownTimerAdapter mCountdownTimerAdapter;
    private List<String> mListItem = new ArrayList<>();
    private static final int TIMER_COUNTDOWN = 18000;
    private static final int COUNTDOWN_INTERVAL = 5000;
    private int mCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        mRecyclerViewCountdown = findViewById(R.id.recyclerViewCountdown);
        initData();
        initAdapter();
        new CountDownTimer(TIMER_COUNTDOWN, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                ++mCount;
                if (mCount % 2 == 0) {
                    mListItem.add("Item " + (mListItem.size() + 1));
                    mListItem.add("Item " + (mListItem.size() + 1));
                }
                if (mCount % 3 == 0) {
                    mListItem.remove(mListItem.size() / 2);
                }
                mCountdownTimerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {
                onDestroy();
            }
        }.start();
    }

    private void initAdapter() {
        mCountdownTimerAdapter = new CountdownTimerAdapter(mListItem);
        mRecyclerViewCountdown.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountdown.setAdapter(mCountdownTimerAdapter);
    }

    private void initData() {
        mListItem.add("Item 1");
        mListItem.add("Item 2");
        mListItem.add("Item 3");
    }
}
