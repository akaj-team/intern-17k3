package vn.asiantech.internship.recycler.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class PersonViewActivity extends AppCompatActivity {
    protected PersonAdapter mAdapter;
    private RecyclerView mRecyclerViewPerson;
    private List<Person> mPersonList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_list);

        initViews();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new PersonAdapter(mPersonList);
        mRecyclerViewPerson.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPerson.setAdapter(mAdapter);
    }

    private void initData() {
        mPersonList.add(new Person(getString(R.string.person_2), (getString(R.string.value_0)), (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_3), (getString(R.string.value_0)), (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_4), (getString(R.string.value_0)), (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_5), (getString(R.string.value_0)), (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_6), (getString(R.string.value_0)), (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_7), (getString(R.string.value_0)), (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_8), (getString(R.string.value_0)), (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_1), (getString(R.string.value_0)), (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_2), (getString(R.string.value_0)), (getString(R.string.text_status_standard))));

    }

    private void initViews() {
        mRecyclerViewPerson = findViewById(R.id.recyclerViewPerson);
    }
}
