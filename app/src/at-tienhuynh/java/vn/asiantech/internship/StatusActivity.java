package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.adapters.StatusAdapter;
import vn.asiantech.internship.models.Status;

public class StatusActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewStatus;
    private List<Status> mStatusList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewStatus = findViewById(R.id.recyclerViewStatus);
    }

    private void initData() {
        mStatusList.add(new Status("Im Tien", "Im 20 years old",1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old",1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old",1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old",1));
    }

    private void initAdapter() {
        StatusAdapter statusAdapter = new StatusAdapter(mStatusList);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(statusAdapter);
    }
}

