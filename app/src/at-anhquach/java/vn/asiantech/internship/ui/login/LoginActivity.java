package vn.asiantech.internship.ui.login;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 11/23/17.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        replace(new LoginButtonFragment());

    }
    public void replace(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frLogin,fragment).addToBackStack(null)
                .commit();
    }
}
