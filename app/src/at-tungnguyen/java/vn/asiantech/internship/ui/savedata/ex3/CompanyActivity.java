package vn.asiantech.internship.ui.savedata.ex3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.sql.SQLiteActivity;
import vn.asiantech.internship.ui.savedata.ex3.model.Company;

/**
 * Display Company of User
 */
public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSloganCompany;
    private TextView mTvIdCompany;
    private static final String KEY_ID_USER = "id_user";
    SQLiteActivity sqLite = new SQLiteActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initView();
        initData();
    }

    /**
     * initView Company
     */
    private void initView() {
        mTvIdCompany = findViewById(R.id.tvCompanyId);
        mTvNameCompany = findViewById(R.id.tvCompanyName);
        mTvSloganCompany = findViewById(R.id.tvCompanySlogan);
    }

    /**
     * setText Company
     */
    private void initData() {
        int idCompany = getIntent().getIntExtra(KEY_ID_USER, -1);
        Company company = sqLite.getCompany(idCompany);
        if (company != null) {
            mTvIdCompany.setText(String.valueOf(company.getId()));
            mTvNameCompany.setText(company.getName());
            mTvSloganCompany.setText(company.getSlogan());
        }
    }
}
