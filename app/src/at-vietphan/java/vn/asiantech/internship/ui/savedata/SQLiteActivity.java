package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.User;

/**
 * Created by vietphan on 07/12/2017
 * SQLiteActivity
 */
public class SQLiteActivity extends AppCompatActivity implements SQLiteAdapter.OnItemClickListener {
    private SQLiteAdapter mSqLiteAdapter;
    private ArrayList<User> mUserList;
    private RecyclerView mRecyclerViewUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        setTitle("SQLite");
        initData();
        initViews();
        initAdapter();
        mRecyclerViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        UserSQLiteHelper mUserSQLiteHelper = new UserSQLiteHelper(this);
        mUserList = mUserSQLiteHelper.getListUser();
        Log.d("list", "list: " + mUserList.get(0).getName());
        mSqLiteAdapter = new SQLiteAdapter(mUserList, this);

    }

    private void initViews() {
        mRecyclerViewUser = findViewById(R.id.recyclerViewSQLite);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapter() {
        mRecyclerViewUser.setAdapter(mSqLiteAdapter);
        Log.d("ll", "onCreate: " + mRecyclerViewUser.toString());
    }

    @Override
    public void onItemUserClick(int position) {
        Intent intent = new Intent(SQLiteActivity.this, CompanyActivity.class);
        intent.putExtra("idUser", mUserList.get(position).getId());
        startActivity(intent);
    }
}
