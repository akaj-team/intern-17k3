package vn.asiantech.internship.ui.savedata.ex3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.savedata.ex3.sql.SQLite;
import vn.asiantech.internship.ui.savedata.ex3.model.Company;

public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSologan;
    private TextView mTvID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initView();
        initData();
    }

    private void initData() {
        int idCompany = getIntent().getIntExtra("id", -1);
        SQLite sqLite = new SQLite(this);
        Company company = sqLite.getCompany(idCompany);
        mTvID.setText(String.valueOf(company.getId()));
        mTvNameCompany.setText(company.getName());
        mTvSologan.setText(company.getSlogan());

    }

    private void initView() {
        mTvID = findViewById(R.id.tvIDCompany);
        mTvNameCompany = findViewById(R.id.tvNameCompany);
        mTvSologan = findViewById(R.id.tvSologanCompany);
    }

}
