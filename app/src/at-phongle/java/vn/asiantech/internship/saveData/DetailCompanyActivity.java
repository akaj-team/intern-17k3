package vn.asiantech.internship.saveData;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.data.DBManager;
import vn.asiantech.internship.models.Company;

/**
 * Created by phongle on 11/12/2560.
 * DetailCompanyActivity
 */
public class DetailCompanyActivity extends AppCompatActivity {
    private static final String PERSON_ID_KEY = "id";
    private TextView mTvCompanyName;
    private TextView mTvCompanySlogan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_company);
        initViews();
        updateViews();
    }

    private void initViews() {
        mTvCompanyName = findViewById(R.id.tvCompanyName);
        mTvCompanySlogan = findViewById(R.id.tvCompanySlogan);
    }

    private void updateViews() {
        Intent intent = getIntent();
        DBManager dbManager = new DBManager(this);
        int id = intent.getIntExtra(PERSON_ID_KEY, 1);
        List<Company> companyList = dbManager.getCompanyByPersonId(id);
        if (companyList != null) {
            mTvCompanyName.setText(companyList.get(0).getName());
            mTvCompanySlogan.setText(companyList.get(0).getSlogan());
        }
    }
}
