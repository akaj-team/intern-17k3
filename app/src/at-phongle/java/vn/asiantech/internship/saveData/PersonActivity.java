package vn.asiantech.internship.saveData;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.data.DBManager;
import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.Person;

/**
 * Created by phongle on 11/12/2560.
 * PersonActivity
 */
public class PersonActivity extends AppCompatActivity implements PersonAdapter.OnItemClickListener {
    private static final String PERSON_ID_KEY = "id";
    private RecyclerView mRecyclerViewPerson;
    private List<Person> mPersonList = new ArrayList<>();
    private List<Company> mCompanyList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewPerson = findViewById(R.id.recyclerViewPerson);
    }

    private void initAdapter() {
        PersonAdapter personAdapter = new PersonAdapter(mPersonList, this);
        mRecyclerViewPerson.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPerson.setAdapter(personAdapter);
    }

    private void initData() {
        List<Person> list;
        DBManager mDBManager = new DBManager(this);
        list = mDBManager.getPerson();
        if (list.size() < 3) {
            Person person1 = new Person("Le Thanh Phong", 23);
            Person person2 = new Person("Cristiano Ronaldo", 33);
            Person person3 = new Person("Zlatan Ibrahimovic", 20);
            Company company1 = new Company("Manchester United", "Manchester is red!");
            Company company2 = new Company("Real Madrid", "Galacticos 2.0!");
            Company company3 = new Company("AsianTech", "Recording History");
            mDBManager.addPerson(person1);
            mDBManager.addPerson(person2);
            mDBManager.addPerson(person3);
            mDBManager.addCompany(company1);
            mDBManager.addCompany(company2);
            mDBManager.addCompany(company3);
            mPersonList.addAll(mDBManager.getPerson());
            mCompanyList.addAll(mDBManager.getCompany());
            mDBManager.addEmployee(mPersonList.get(0), mCompanyList.get(2));
            mDBManager.addEmployee(mPersonList.get(1), mCompanyList.get(1));
            mDBManager.addEmployee(mPersonList.get(2), mCompanyList.get(0));
        } else {
            mPersonList = list;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailCompanyActivity.class);
        intent.putExtra(PERSON_ID_KEY, mPersonList.get(position).getId());
        startActivity(intent);
    }
}
