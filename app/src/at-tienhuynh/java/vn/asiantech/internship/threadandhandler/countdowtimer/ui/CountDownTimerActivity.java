package vn.asiantech.internship.threadandhandler.countdowtimer.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.threadandhandler.countdowtimer.adapters.ListCountDownTimerAdapter;
import vn.asiantech.internship.threadandhandler.countdowtimer.models.Item;

/**
 * Created at 2017
 * Created by jackty on 20/12/2017.
 */
public class CountDownTimerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewCountDownTimer;
    private List<Item> mItemList = new ArrayList<>();
    private TextView mTvCountDown;
    private ListCountDownTimerAdapter mListCountDownTimerAdapter;
    private int mCountTime;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initData();
        initViews();
        initAdapters();
        // start count down
        countDownTimer.start();
    }

    /**
     * Init Data
     */
    private void initData() {
        mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
        mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
        mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
        mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
    }

    /**
     * Init Views
     */
    private void initViews() {
        mRecyclerViewCountDownTimer = findViewById(R.id.recyclerViewCountDownTimer);
        mTvCountDown = findViewById(R.id.tvCountDown);
    }

    /**
     * Init Adapter
     */
    private void initAdapters() {
        mListCountDownTimerAdapter = new ListCountDownTimerAdapter(mItemList);
        mRecyclerViewCountDownTimer.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountDownTimer.setAdapter(mListCountDownTimerAdapter);
    }

    /**
     * Destroy Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    /**
     * This is count down timer
     */
    CountDownTimer countDownTimer = new CountDownTimer(180000, 1000) {
        @Override
        public void onTick(long l) {
            mCountTime = (int) (l / 1000);
            mTvCountDown.setText(String.valueOf(mCountTime));
        }

        @Override
        public void onFinish() {
            mTvCountDown.setText(getResources().getString(R.string.number_0));
            mCountTime = 0;
        }
    };

    /**
     * This method to add 2 item after 10 second
     */
    CountDownTimer mAddItemTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            // No-opp
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
                mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
                mListCountDownTimerAdapter.notifyDataSetChanged();
                mAddItemTimer.start();
            }
        }
    }.start();

    /**
     * This method is used to delete middle item after 15 seconds
     */
    CountDownTimer mDeleteItemTimer = new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long l) {
            // no-opp
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mItemList.remove(mItemList.size() / 2);
                mListCountDownTimerAdapter.notifyDataSetChanged();
                mDeleteItemTimer.start();
            }
        }
    }.start();
}
