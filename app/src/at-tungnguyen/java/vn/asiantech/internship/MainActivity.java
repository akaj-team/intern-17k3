
package vn.asiantech.internship;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.models.Person;
import vn.asiantech.internship.models.PersonAdapter;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerViewPerson;
    private List<Person> mPersonList = new ArrayList<>();
    private PersonAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdapter();
        initData();
        initViews();
    }

    private void initAdapter() {
        mAdapter = new PersonAdapter(mPersonList);
    }

    private void initData() {
        mPersonList.add(new Person("Nguyen Thanh Tung","abc",""));
        mPersonList.add(new Person("Nguyen Thanh Tung2","abc",""));
        mPersonList.add(new Person("Nguyen Thanh Tung3","abc",""));

    }

    private void initViews() {
        mRecyclerViewPerson = findViewById(R.id.recyclerViewPerson);
        mRecyclerViewPerson.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPerson.setAdapter(mAdapter);
    }




}
