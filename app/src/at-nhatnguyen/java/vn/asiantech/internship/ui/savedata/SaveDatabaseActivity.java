package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.UserCompany;

public class SaveDatabaseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewUser;
    private List<UserCompany> mUserCompanies;
    private Company mCompany;

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
        mUserCompanies = new ArrayList<>();
        UserCompanySQLite mUserCompanySQLite = new UserCompanySQLite(this);
        mUserCompanySQLite.insertUSER(1, "A", 20);
        mUserCompanySQLite.insertUSER(2, "B", 20);
        mUserCompanySQLite.insertUSER(3, "C", 20);
        mUserCompanySQLite.insertUSER(4, "D", 22);
        mUserCompanySQLite.insertUSER(5, "E", 22);
        mUserCompanySQLite.insertUSER(6, "F", 22);
        mUserCompanySQLite.insertUSER(7, "G", 22);
        mUserCompanySQLite.insertCompany(1,"ASIANTECH","recoding history");
        mUserCompanySQLite.insertCompany(2,"FPT","coding");
        mUserCompanies = mUserCompanySQLite.getUserCompany();
        mCompany=mUserCompanySQLite.getCompany(1);
    }

    private void initAdapter() {
        UserCompanyAdapter userCompanyAdapter;
        userCompanyAdapter = new UserCompanyAdapter(mUserCompanies);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUser.setAdapter(userCompanyAdapter);
    }
}
