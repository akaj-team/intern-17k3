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

    private static final int TOTAL_TIME = 180000;
    private static final int DELETE_ITEM_TIME = 15;
    private static final int ADD_ITEM_TIME = 10;
    private static final int INTERVAL_TIME = 1000;
    private RecyclerView mRecyclerViewCountDownTimer;
    private List<Item> mItemList = new ArrayList<>();
    private TextView mTvCountDown;
    private ListCountDownTimerAdapter mListCountDownTimerAdapter;
    private int mCountTimeAdd = 0;
    private int mCountTimeDelete = 0;
    private int mCountTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initData();
        initViews();
        initAdapter();
        // start count down
        mCountDownTimer.start();
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
    private void initAdapter() {
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
        mCountDownTimer.cancel();
    }

    /**
     * This is count down timer
     */
    CountDownTimer mCountDownTimer = new CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {
        @Override
        public void onTick(long l) {
            mCountTime = (int) (l / 1000);
            mTvCountDown.setText(String.valueOf(mCountTime));
            if (mCountTime > 0) {
                mCountTimeAdd++;
                mCountTimeDelete++;
                if (mCountTimeAdd == ADD_ITEM_TIME) {
                    addItem();
                    mCountTimeAdd = 0;
                }
                if (mCountTimeDelete == DELETE_ITEM_TIME) {
                    deleteItem();
                    mCountTimeDelete = 0;
                }
            }
        }

        @Override
        public void onFinish() {
            mTvCountDown.setText(getResources().getString(R.string.number_0));
            mCountTime = 0;
        }

        /**
         * This method to add 2 item after 10 second
         */
        private void addItem() {
            mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
            mItemList.add(new Item(getResources().getString(R.string.item_countdown_list)));
            mListCountDownTimerAdapter.notifyDataSetChanged();
        }

        /**
         * This method is used to delete middle item after 15 seconds
         */
        private void deleteItem() {
            mItemList.remove(mItemList.size() / 2);
            mListCountDownTimerAdapter.notifyDataSetChanged();
        }
    };
}
