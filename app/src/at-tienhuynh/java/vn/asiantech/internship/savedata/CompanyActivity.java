package vn.asiantech.internship.savedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.savedata.models.Company;
import vn.asiantech.internship.savedata.sqlite.UsersDatabase;

public class CompanyActivity extends AppCompatActivity {
    private int mIdUser;
    private UsersDatabase mUsersDatabase = new UsersDatabase(this);
    private TextView mTvCompanyName;
    private TextView mTvCompanySlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        getIdUser();
        initViews();
        initData();
    }

    /**
     * Get id_user from SQLite Activity
     */
    private void getIdUser() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIdUser = bundle.getInt("id_user");
        }
    }

    /**
     * Init Views
     */
    private void initViews() {
        mTvCompanyName = findViewById(R.id.tvCompanyName);
        mTvCompanySlogan = findViewById(R.id.tvCompanySlogan);
    }

    /**
     * Init Data to EditText
     */
    private void initData() {
        Company company = mUsersDatabase.getCompany(mIdUser);
        if (company != null) {
            mTvCompanyName.setText(getString(R.string.company_name) + company.getName());
            mTvCompanySlogan.setText(getString(R.string.company_slogan) + company.getSlogan());
        }
    }
}
