package vn.asiantech.internship.save_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.data.DBManager;
import vn.asiantech.internship.models.Company;

public class DetailCompanyActivity extends AppCompatActivity {
    private DBManager mDBManager;
    private TextView mTvCompanyName;
    private TextView mTvCompanySlogan;
    private List<Company> mCompanyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_company);
        initViews();
        setViews();
    }

    private void initViews() {
        mTvCompanyName = findViewById(R.id.tvCompanyName);
        mTvCompanySlogan = findViewById(R.id.tvCompanySlogan);
    }

    private void setViews() {
        Intent intent = getIntent();
        mDBManager = new DBManager(this);
        int id = intent.getIntExtra("id", 1);
        Log.d("vv", String.valueOf(id));
        mCompanyList = mDBManager.getCompanyByPersonId(id);
        if (mCompanyList != null) {
            mTvCompanyName.setText(mCompanyList.get(0).getName());
            mTvCompanySlogan.setText(mCompanyList.get(0).getSlogan());
        }
    }
}
