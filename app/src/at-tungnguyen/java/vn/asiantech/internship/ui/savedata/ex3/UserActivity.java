package vn.asiantech.internship.ui.savedata.ex3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.adapter.UserAdapter;
import vn.asiantech.internship.ui.savedata.ex3.sql.SQLite;
import vn.asiantech.internship.ui.savedata.ex3.model.Company;
import vn.asiantech.internship.ui.savedata.ex3.model.Employee;
import vn.asiantech.internship.ui.savedata.ex3.model.User;

public class UserActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    SQLite sqLite = new SQLite(this);
    ArrayList<User> sqLiteUser;

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

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerviewUser);
    }

    private void initAdapterUser() {
        sqLiteUser = sqLite.getUser();
        UserAdapter userAdapter = new UserAdapter(sqLiteUser, this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(userAdapter);
    }

    private void initDataCompany() {
        sqLite.addCompany(new Company(1, "Asian Tes", "Recording Historys"));
        sqLite.addCompany(new Company(2, "Asian Tech", "Recording History1"));
        sqLite.addCompany(new Company(3, "Asian Tech1", "Recording Historya"));
    }

    private void initDataUser() {
        sqLite.addUser(new User(1, "Tung", 20));
        sqLite.addUser(new User(2, "Tien", 20));
        sqLite.addUser(new User(3, "Tiep", 20));
    }

    private void initDataEmployee() {
        for (int i = 0; i < 5; i++) {
            sqLite.addEmployee(new Employee(i + 1, i + 1, i + 1));
        }
    }

    @Override
    public void onItemClickListener(int potion) {
        Intent i = new Intent(this, CompanyActivity.class);
        i.putExtra("id", sqLiteUser.get(potion).getId());
        startActivity(i);
    }
}
