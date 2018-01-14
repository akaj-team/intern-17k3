package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.User;

/**
 * User SQLite DownloadActivity
 */
public class UserSQLiteActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewUser;
    private List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sqlite);
        initViews();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewUser = findViewById(R.id.recyclerViewUser);
    }

    private void initAdapter() {
        UserSQLiteHelper userSQLiteHelper = new UserSQLiteHelper(this);
        mUserList = userSQLiteHelper.getListUser();
        UserAdapter mAdapter = new UserAdapter(mUserList, this);
        mRecyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUser.setAdapter(mAdapter);
    }

    @Override
    public void onItemUserClick(int position) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra(getResources().getString(R.string.key_id_user), mUserList.get(position).getId());
        startActivity(intent);
    }
}
