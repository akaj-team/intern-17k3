package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.savedata.adapters.UsersAdapter;
import vn.asiantech.internship.savedata.models.Company;
import vn.asiantech.internship.savedata.models.Employee;
import vn.asiantech.internship.savedata.models.Users;
import vn.asiantech.internship.savedata.sqlite.UsersDatabase;

public class SqLiteActivity extends AppCompatActivity implements UsersAdapter.onItemClick {
    private RecyclerView mRecyclerViewUsers;
    private UsersDatabase mUersDatabase = new UsersDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);
        initViews();
//        initDataUsers();
//        initDataCompany();
//        initDataEmPloyee();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewUsers = findViewById(R.id.recyclerViewListUser);
    }

    /**
     * Add Data Table Users
     */
    private void initDataUsers() {
        mUersDatabase.addUsers(new Users("Tien Huynh", 20));
        mUersDatabase.addUsers(new Users("Tung Nguyen", 20));
        mUersDatabase.addUsers(new Users("Anh Quach", 19));
        mUersDatabase.addUsers(new Users("Viet Phan", 22));
        mUersDatabase.addUsers(new Users("Nhat Nguyen", 22));
    }

    /**
     * Add Data Table Company
     */
    private void initDataCompany() {
        mUersDatabase.addCompany(new Company("Asian Tech", "Recoding History"));
        mUersDatabase.addCompany(new Company("FPT", "abcxyz"));
        mUersDatabase.addCompany(new Company("Minori", "cccccc"));
        mUersDatabase.addCompany(new Company("BAP", "ddddddd"));
        mUersDatabase.addCompany(new Company("Smart Dev", "eeeeeeee"));
    }

    /**
     * Add Data Table Employee
     */
    private void initDataEmPloyee() {
        for (int i = 0; i < 5; i++) {
            mUersDatabase.addEmployee(new Employee(i + 1, i + 1));
        }
    }

    /**
     * Set Adapter to RecyclerView
     */
    private void initAdapter() {
        UsersAdapter usersAdapter = new UsersAdapter(mUersDatabase.getAllUsers(), this);
        mRecyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUsers.setAdapter(usersAdapter);
    }

    /**
     * OnClick Item RecyclerView
     */
    @Override
    public void onItemClickListener(int potion) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra("id_user", mUersDatabase.getAllUsers().get(potion).getId());
        startActivity(intent);
    }
}
