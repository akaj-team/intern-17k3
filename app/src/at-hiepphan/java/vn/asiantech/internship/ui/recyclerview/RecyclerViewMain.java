package vn.asiantech.internship.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.DataContent;

public class RecyclerViewMain extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private List<DataContent> mDataContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mDataContent = new ArrayList<>();
        mDataContent.add(new DataContent("AAA", "hoa huong duong", 0));
        mDataContent.add(new DataContent("BB", "hoa ho ho", 0));
        mDataContent.add(new DataContent("C", "hoa hom ho", 0));
        mDataContent.add(new DataContent("D", "hoa hom hoa", 0));
        mDataContent.add(new DataContent("E", "hoa hoa hoa", 0));
        mDataContent.add(new DataContent("F", "hoa hong", 0));
        mRecyclerAdapter = new RecyclerAdapter(mDataContent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
