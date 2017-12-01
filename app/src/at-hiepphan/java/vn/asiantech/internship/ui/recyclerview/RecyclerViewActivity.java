package vn.asiantech.internship.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.Content;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewContent;
    private RecyclerAdapter mAdapter;
    private List<Content> mContents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewContent = findViewById(R.id.recyclerView);
    }

    private void initData() {
        mContents.add(new Content("AAA", "hoa huong duong", 0));
        mContents.add(new Content("BB", "hoa ho ho", 2));
        mContents.add(new Content("C", "hoa hom ho", -2));
        mContents.add(new Content("D", "hoa hom hoa", 0));
        mContents.add(new Content("E", "hoa hoa hoa", 0));
        mContents.add(new Content("F", "hoa hong", 0));
    }

    private void initAdapter() {
        mAdapter = new RecyclerAdapter(mContents);
        mRecyclerViewContent.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewContent.setAdapter(mAdapter);
    }
}
