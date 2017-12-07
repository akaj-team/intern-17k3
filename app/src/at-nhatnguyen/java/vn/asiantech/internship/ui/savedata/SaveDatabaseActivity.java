package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.People;

/**
 * Activity display list people
 */
public class SaveDatabaseActivity extends AppCompatActivity implements PeopleAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewUser;
    private List<People> mPeoples;
    private PeopleSQLite mPeopleSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_database);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        mRecyclerViewUser = findViewById(R.id.recycleViewUser);
    }

    private void initData() {
        mPeoples = new ArrayList<>();
        mPeopleSQLite = new PeopleSQLite(this);
        mPeopleSQLite.insertUSER(51, "A", 20);
        mPeopleSQLite.insertUSER(52, "B", 20);
        mPeopleSQLite.insertUSER(53, "C", 20);
        mPeopleSQLite.insertUSER(54, "D", 22);
        mPeopleSQLite.insertUSER(55, "E", 22);
        mPeopleSQLite.insertUSER(56, "F", 22);
        mPeopleSQLite.insertUSER(57, "G", 22);
        mPeopleSQLite.insertCompany(101, "ASIANTECH", "recoding history");
        mPeopleSQLite.insertCompany(102, "FPT", "coding");
        mPeopleSQLite.insertCompany(103, "GAMELOFT", "coding");
        mPeopleSQLite.insertCompany(104, "AXON", "coding");
        mPeopleSQLite.insertEmployee(51, 101);
        mPeopleSQLite.insertEmployee(52, 101);
        mPeopleSQLite.insertEmployee(53, 102);
        mPeopleSQLite.insertEmployee(54, 103);
        mPeopleSQLite.insertEmployee(55, 102);
        mPeopleSQLite.insertEmployee(56, 102);
        mPeopleSQLite.insertEmployee(57, 103);
        mPeoples = mPeopleSQLite.getUserCompany();
    }

    private void initAdapter() {
        PeopleAdapter userCompanyAdapter;
        userCompanyAdapter = new PeopleAdapter(mPeoples, this);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUser.setAdapter(userCompanyAdapter);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra("idCompany", mPeopleSQLite.getIdCompany(mPeoples.get(position).getiDPeople()));
        startActivity(intent);
    }
}
