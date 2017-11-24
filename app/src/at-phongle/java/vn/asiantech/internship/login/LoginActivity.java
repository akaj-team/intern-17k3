package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import vn.asiantech.internship.R;

public class LoginActivity extends AppCompatActivity {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        addFragment(LoginFragment.getInstance(), false);

    }

    protected void addFragment(Fragment fragment, boolean addBackStack) {
        if (addBackStack) {
            mImgBack.setSelected(true);
        } else {
            mImgBack.setSelected(false);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frlContainer, fragment);
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }
}
