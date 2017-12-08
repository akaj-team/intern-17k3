package vn.asiantech.internship.savedata;

/*
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Company;

public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initViews();
        Intent intent = getIntent();
        int id = intent.getIntExtra(getResources().getString(R.string.key_id_user), 0);
        UserSQLiteHelper userSQLiteHelper = new UserSQLiteHelper(this);
        Company company = userSQLiteHelper.getCompanyByUserId(id);
        if (company != null) {
            mTvNameCompany.setText(company.getName());
            mTvSlogan.setText(company.getSlogan());
        }
    }

    private void initViews() {
        mTvNameCompany = findViewById(R.id.tvNameCompany);
        mTvSlogan = findViewById(R.id.tvSlogan);
    }
}
