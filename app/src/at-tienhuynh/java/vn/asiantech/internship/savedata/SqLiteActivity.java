package vn.asiantech.internship.savedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.savedata.sqlite.UsersDatabase;

public class SqLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);
        UsersDatabase usersDatabase = new UsersDatabase(this);
    }
}
