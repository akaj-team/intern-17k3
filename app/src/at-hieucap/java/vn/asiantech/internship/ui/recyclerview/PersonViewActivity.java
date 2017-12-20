package vn.asiantech.internship.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.recyclerview.Person;

/**
 * Create Person View Activity
 */
public class PersonViewActivity extends AppCompatActivity implements PersonAdapter.OnItemClickListener {
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

    private void initViews() {
        mRecyclerViewPerson = findViewById(R.id.recyclerViewPerson);
    }

    private void initAdapter() {
        mAdapter = new PersonAdapter(mPersonList, this);
        mRecyclerViewPerson.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPerson.setAdapter(mAdapter);
    }

    private void initData() {
        mPersonList.add(new Person(getString(R.string.person_2), 9, (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_3), 10, (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_4), 11, (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_5), -2, (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_6), 4, (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_7), -6, (getString(R.string.text_status_standard))));
        mPersonList.add(new Person(getString(R.string.person_8), -5, (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_1), 0, (getString(R.string.text_status_max_length))));
        mPersonList.add(new Person(getString(R.string.person_2), -9, (getString(R.string.text_status_standard))));
    }

    @Override
    public void onLikeClick(int position) {
        mPersonList.get(position).setCountLike(mPersonList.get(position).getCountLike() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDislikeClick(int position) {
        mPersonList.get(position).setCountLike(mPersonList.get(position).getCountLike() - 1);
        mAdapter.notifyItemChanged(position);
    }
}
