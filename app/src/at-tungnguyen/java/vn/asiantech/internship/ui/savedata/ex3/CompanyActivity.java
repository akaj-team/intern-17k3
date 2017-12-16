package vn.asiantech.internship.ui.savedata.ex3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.sql.SQLite;
import vn.asiantech.internship.ui.savedata.ex3.model.Company;

/**
 * Display Company of User
 */
public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSologan;
    private TextView mTvId;
    private static final String KEY_ID_USER = "id_user";
    SQLite sqLite = new SQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initView();
        initData();
    }

    private void initView() {
        mTvId = findViewById(R.id.tvCompanyId);
        mTvNameCompany = findViewById(R.id.tvCompanyName);
        mTvSologan = findViewById(R.id.tvCompanySlogan);
    }

    private void initData() {
        int idCompany = getIntent().getIntExtra(KEY_ID_USER, -1);
        Company company = sqLite.getCompany(idCompany);
        if (company != null) {
            mTvId.setText(String.valueOf(company.getId()));
            mTvNameCompany.setText(company.getName());
            mTvSologan.setText(company.getSlogan());
        }
    }
}
