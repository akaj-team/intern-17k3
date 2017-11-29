package vn.asiantech.internship.ui.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.recyclerview.model.DataContent;

public class RecyclerViewMain extends AppCompatActivity {
    private RecyclerView mRecyclerView ;
    private RecyclerAdapter mRecyclerAdapter;
    private List<DataContent> mDataContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        mDataContent = new ArrayList<>();
        mDataContent.add(new DataContent("AAA","ho ho ho"));
        mDataContent.add(new DataContent("BB","hoa ho ho"));
        mDataContent.add(new DataContent("CC","hoa hom ho"));

        mRecyclerAdapter = new RecyclerAdapter(mDataContent);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);


    }
}
