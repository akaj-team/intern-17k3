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
        mPeopleSQLite.insertUSER(51, "Nguyễn Văn A", 20);
        mPeopleSQLite.insertUSER(52, "Trần Thị B", 21);
        mPeopleSQLite.insertUSER(53, "Phan Xuân Việt", 32);
        mPeopleSQLite.insertUSER(54, "Nguyễn Văn Tám", 20);
        mPeopleSQLite.insertUSER(55, "Hoàng Mình Tuấn", 18);
        mPeopleSQLite.insertUSER(56, "Đỗ Quang", 22);
        mPeopleSQLite.insertUSER(57, "Phạm Thị Lan Phương", 22);
        mPeopleSQLite.insertCompany(101, "ASIAN TECH", "Recoding History");
        mPeopleSQLite.insertCompany(102, "FPT", "coding");
        mPeopleSQLite.insertCompany(103, "GAME LOFT", "coding");
        mPeopleSQLite.insertCompany(104, "AXON", "coding");
        mPeopleSQLite.insertEmployee(51, 101);
        mPeopleSQLite.insertEmployee(52, 101);
        mPeopleSQLite.insertEmployee(53, 101);
        mPeopleSQLite.insertEmployee(54, 102);
        mPeopleSQLite.insertEmployee(55, 103);
        mPeopleSQLite.insertEmployee(56, 104);
        mPeopleSQLite.insertEmployee(57, 101);
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
