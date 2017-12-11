package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.User;

/**
 * Created by vietphan on 07/12/2017
 * CompanyActivity
 */
public class CompanyActivity extends AppCompatActivity {
    private static final String ID_USER = "idUser";
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvCompanyName;
    private TextView mTvSlogan;
    private int mIdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Intent intent = getIntent();
        mIdUser = intent.getIntExtra(ID_USER, 0);
        initViews();
        initAdapter();
    }

    private void initViews() {
        mTvName = findViewById(R.id.tvName);
        mTvAge = findViewById(R.id.tvAge);
        mTvCompanyName = findViewById(R.id.tvCompanyName);
        mTvSlogan = findViewById(R.id.tvSlogan);
    }

    private void initAdapter() {
        UserSQLiteHelper userSQLiteHelper = new UserSQLiteHelper(this);
        Company company = userSQLiteHelper.getCompanyByIdUser(mIdUser);
        User user = userSQLiteHelper.getUser(mIdUser);
        if (!user.equals(null)) {
            mTvName.setText(user.getName());
            mTvAge.setText(String.valueOf(user.getAge()));
            mTvCompanyName.setText(company.getName());
            mTvSlogan.setText(company.getSlogan());
        }
    }
}
