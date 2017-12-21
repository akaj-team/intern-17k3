package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
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
    private List<String> mListItem = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        mRecyclerViewCountdown = findViewById(R.id.recyclerViewCountdown);
        initData();
        initAdapter();
    }
    private void initAdapter(){
        CountdownTimerAdapter countdownTimerAdapter = new CountdownTimerAdapter(mListItem);
        mRecyclerViewCountdown.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountdown.setAdapter(countdownTimerAdapter);
    }
    private void initData(){
        mListItem.add("Item 1");
        mListItem.add("Item 2");
        mListItem.add("Item 3");
    }
}
