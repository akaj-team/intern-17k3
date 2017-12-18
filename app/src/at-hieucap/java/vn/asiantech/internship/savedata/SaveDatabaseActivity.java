package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.People;

public class SaveDatabaseActivity extends AppCompatActivity implements PeopleAdapter.OnItemClickListener {
    static final String KEY_ID_COMPANY = "id_company";
    private RecyclerView mRecyclerViewUser;
    private PeopleSQLite mPeopleSQLite;
    private List<People> mPeoples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_database);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewUser = findViewById(R.id.recycleViewUser);
    }

    private void initData() {
        mPeoples = new ArrayList<>();
        mPeopleSQLite = new PeopleSQLite(this);
        mPeopleSQLite.insertUSER(1, "Anh A", 20);
        mPeopleSQLite.insertUSER(2, "Anh B", 21);
        mPeopleSQLite.insertUSER(3, "Anh C", 20);
        mPeopleSQLite.insertUSER(4, "Anh D", 18);
        mPeopleSQLite.insertUSER(5, "Anh E", 18);
        mPeopleSQLite.insertUSER(6, "Anh F", 22);
        mPeopleSQLite.insertUSER(8, "Anh G", 25);
        mPeopleSQLite.insertUSER(9, "Anh H", 20);
        mPeopleSQLite.insertUSER(10, "Anh I", 23);
        mPeopleSQLite.insertCompany(11, "Company A", "CEO");
        mPeopleSQLite.insertCompany(12, "Company B", "CFO");
        mPeopleSQLite.insertCompany(13, "Company C", "CPO");
        mPeopleSQLite.insertEmployee(1, 11);
        mPeopleSQLite.insertEmployee(2, 12);
        mPeopleSQLite.insertEmployee(3, 13);
        mPeopleSQLite.insertEmployee(4, 12);
        mPeopleSQLite.insertEmployee(5, 13);
        mPeopleSQLite.insertEmployee(6, 11);
        mPeopleSQLite.insertEmployee(7, 13);
        mPeopleSQLite.insertEmployee(8, 13);
        mPeopleSQLite.insertEmployee(9, 12);
        mPeopleSQLite.insertEmployee(10, 11);
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
        intent.putExtra(KEY_ID_COMPANY, mPeopleSQLite.getIdCompany(mPeoples.get(position).getIDPeople()));
        startActivity(intent);
    }
}
