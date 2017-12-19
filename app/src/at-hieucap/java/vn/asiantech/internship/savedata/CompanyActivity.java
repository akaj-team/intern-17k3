package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Company;

/**
 * Create company activity
 */
public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSlogan;
    private PeopleSQLite mPeopleSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_company);
        initViews();
        initData();
    }

    private void initViews() {
        mTvNameCompany = findViewById(R.id.tvNameCompany);
        mTvSlogan = findViewById(R.id.tvSlogan);
        mPeopleSQLite = new PeopleSQLite(this);
    }

    private void initData() {
        Intent intent = getIntent();
        int idCompany = intent.getIntExtra(SaveDatabaseActivity.KEY_ID_COMPANY, 0);
        if (idCompany != 0) {
            Company company = mPeopleSQLite.getCompany(idCompany);
            if (company != null) {
                mTvNameCompany.setText(company.getNameCompany());
                mTvSlogan.setText(company.getSloganCompany());
            } else {
                mTvNameCompany.setText(R.string.event_company_is_empty);
                mTvSlogan.setText(R.string.slogan_is_empty);
            }
        } else {
            mTvNameCompany.setText(R.string.event_company_is_empty);
            mTvSlogan.setText(R.string.slogan_is_empty);
        }
    }
}
