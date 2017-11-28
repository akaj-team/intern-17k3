package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.model.NewFeed;

/**
 * Created by anh.quach on 11/28/17.
 */

public class RecyclerViewActivity extends AppCompatActivity{
    private RecyclerView mRecyclerViewNewFeed;
    private List<NewFeed> mNewFeedList = new ArrayList<>();
    private NewFeedAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initViews();
        initData();
        initAdapter();
    }
    private void initViews(){
        mRecyclerViewNewFeed = findViewById(R.id.recyclerViewPerson);
    }
    private void initData() {
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana","How to complete this issue?"));
    }
    private void initAdapter(){
        mAdapter = new NewFeedAdapter(mNewFeedList);
        mRecyclerViewNewFeed.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewNewFeed.setAdapter(mAdapter);
    }
}
