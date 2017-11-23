package vn.asiantech.internship.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Created by phanhiep on 23/11/2017.
 */

public class LoginActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        replace(new LoginFragment());
    }
    public void replace(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frmMain,fragment)
                .commit();
    }


}
