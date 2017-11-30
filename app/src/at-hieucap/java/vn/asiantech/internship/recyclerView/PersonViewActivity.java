package vn.asiantech.internship.recyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;



public class PersonViewActivity extends AppCompatActivity{
    private RecyclerView mRecyclerViewPerson;
    private List<Person> mPersonList= new ArrayList<>();
    protected PersonAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_list);

        initViews();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new PersonAdapter(this, mPersonList );
        mRecyclerViewPerson.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPerson.setAdapter(mAdapter);
    }

    private void initData() {
        mPersonList.add(new Person("Anh A", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh B", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh C", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh D", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh E", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh F", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh G", false,false,"0", "This is Sparta"));
        mPersonList.add(new Person("Anh H", false,false,"0", "This is Sparta"));

    }

    private void initViews() {
    mRecyclerViewPerson = findViewById(R.id.recyclerViewPerson);
    }
}
