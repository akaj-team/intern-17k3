package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.model.NewFeed;

public class RecyclerViewActivity extends AppCompatActivity implements NewFeedAdapter.OnButtonClick {
    private RecyclerView mRecyclerViewNewFeed;
    private List<NewFeed> mNewFeedList = new ArrayList<>();
    private NewFeedAdapter mAdapter;
    private TextView mTvSumReact;

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
        mTvSumReact = findViewById(R.id.tvSumReact);
    }

    private void initData() {
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
        mNewFeedList.add(new NewFeed("Hana Hana", "How to complete this issue?"));
    }

    private void initAdapter() {
        mAdapter = new NewFeedAdapter(mNewFeedList);
        mRecyclerViewNewFeed.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewNewFeed.setAdapter(mAdapter);
    }

    @Override
    public void onClickLike(int position) {
        mNewFeedList.get(position).setSumReact(mNewFeedList.get(position).getSumReact() + 1);
        mTvSumReact.setText(mNewFeedList.get(position).getSumReact());
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDisLike(int position) {
        mNewFeedList.get(position).setSumReact(mNewFeedList.get(position).getSumReact() - 1);
        mTvReact.setText(mNewFeedList.get(position).getSumReact());
        mAdapter.notifyItemChanged(position);
    }
}
