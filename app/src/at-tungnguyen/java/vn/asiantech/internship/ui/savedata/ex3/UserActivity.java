package vn.asiantech.internship.ui.savedata.ex3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.adapter.UserAdapter;
import vn.asiantech.internship.ui.savedata.ex3.sql.SQLiteActivity;
import vn.asiantech.internship.ui.savedata.ex3.model.Company;
import vn.asiantech.internship.ui.savedata.ex3.model.Employee;
import vn.asiantech.internship.ui.savedata.ex3.model.User;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class UserActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    SQLiteActivity sqLite = new SQLiteActivity(this);
    private ArrayList<User> mSqLiteUser;
    private static final String KEY_ID_USER = "id_user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initAdapterUser();
        initDataCompany();
        initDataUser();
        initDataEmployee();
    }

    /**
     * initView RecyclerView
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerViewUser);
    }

    /**
     * Set Adapter to RecyclerView
     */
    private void initAdapterUser() {
        mSqLiteUser = sqLite.getUser();
        UserAdapter userAdapter = new UserAdapter(mSqLiteUser, this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(userAdapter);
    }

    /**
     * Add data for Table Company
     */
    private void initDataCompany() {
        sqLite.addCompany(new Company(1, "Asian Tes", "Recording Historys"));
        sqLite.addCompany(new Company(2, "Asian Tech", "Recording History1"));
        sqLite.addCompany(new Company(3, "Asian Tech1", "Recording Historya"));
    }

    /**
     * Add data for Table User
     */
    private void initDataUser() {
        sqLite.addUser(new User(1, "Tung", 20));
        sqLite.addUser(new User(2, "Tien", 20));
        sqLite.addUser(new User(3, "Tiep", 20));
    }

    /**
     * Add data for Table Employee
     */
    private void initDataEmployee() {
        for (int i = 0; i < 5; i++) {
            sqLite.addEmployee(new Employee(i + 1, i + 1, i + 1));
        }
    }

    /**
     * Onclick item RecyclerView
     */
    @Override
    public void onItemClickListener(int position) {
        Intent i = new Intent(this, CompanyActivity.class);
        i.putExtra(KEY_ID_USER, mSqLiteUser.get(position).getId());
        startActivity(i);
    }
}
