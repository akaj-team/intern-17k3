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
     * This is count down timer
     */
    CountDownTimer countDownTimer = new CountDownTimer(18000, 1000) {
        @Override
        public void onTick(long l) {
            mCountTime = (int) (l / 1000);
            mTvCountDown.setText(String.valueOf(mCountTime));
        }

        @Override
        public void onFinish() {
            mTvCountDown.setText(getResources().getString(R.string.number_0));
        }
    };

    /**
     * This method to add item after 3 second
     */
    CountDownTimer addItem = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long l) {
            // No-opp
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
                mListCountDownTimerAdapter.notifyDataSetChanged();
                addItem.start();
            }
        }
    }.start();

    /**
     * This method is used to delete item after 2 seconds
     */
    CountDownTimer deleteItem = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long l) {
            // no-opp
        }

        @Override
        public void onFinish() {
            if (mCountTime > 0) {
                mItemList.remove(mItemList.size() / 2);
                mListCountDownTimerAdapter.notifyDataSetChanged();
                deleteItem.start();
            }
        }
    }.start();
}
