package vn.asiantech.internship.savedata;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

public class UserSQLiteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sqlite);
        UserSQLiteHelper userSQLiteHelper = new UserSQLiteHelper(this);
    }
}
