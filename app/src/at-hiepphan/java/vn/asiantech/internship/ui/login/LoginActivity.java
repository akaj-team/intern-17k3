package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import vn.asiantech.internship.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        replaceFragment(new LoginFragment());
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frMain, fragment).addToBackStack(null)
                .commit();
    }
}
