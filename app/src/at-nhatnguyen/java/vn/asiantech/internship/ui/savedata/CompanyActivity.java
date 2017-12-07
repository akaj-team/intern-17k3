package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Company;

/**
 * Company Activity
 */
public class CompanyActivity extends AppCompatActivity {
    private TextView mTvNameCompany;
    private TextView mTvSlogan;
    private PeopleSQLite mPeopleSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initView();
        initData();
    }

    private void initView() {
        mTvNameCompany = findViewById(R.id.tvNameCompany);
        mTvSlogan = findViewById(R.id.tvSlogan);
        mPeopleSQLite = new PeopleSQLite(this);
    }

    private void initData() {
        Intent intent = getIntent();
        int idCompany = intent.getIntExtra("idCompany", 0);
        Toast.makeText(this, "" + idCompany, Toast.LENGTH_SHORT).show();
        Company mCompany = mPeopleSQLite.getCompany(idCompany);
        mTvNameCompany.setText(mCompany.getNameCompany());
        mTvSlogan.setText(mCompany.getSloganCompany());
    }

}
