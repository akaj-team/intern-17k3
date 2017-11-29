package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.model.NewFeed;

public class RecyclerViewActivity extends AppCompatActivity implements NewFeedAdapter.OnButtonClick {
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

    private void initViews() {
        mRecyclerViewNewFeed = findViewById(R.id.recyclerViewPerson);
    }

    private void initData() {
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhfff", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?", 0));
    }

    private void initAdapter() {
        mAdapter = new NewFeedAdapter(mNewFeedList, this);
        mRecyclerViewNewFeed.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewNewFeed.setAdapter(mAdapter);
    }

    @Override
    public void onClickLike(int position) {
        mNewFeedList.get(position).setSumReact(mNewFeedList.get(position).getSumReact() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDisLike(int position) {
        mNewFeedList.get(position).setSumReact(mNewFeedList.get(position).getSumReact() - 1);
        mAdapter.notifyItemChanged(position);
    }
}
