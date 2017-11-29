package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import vn.asiantech.internship.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        replaceFragment(LoginFragment.getInstance(), false);
        mImgBack.setOnClickListener(this);
    }

    protected void replaceFragment(Fragment fragment, boolean isAdd) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frMain, fragment);
        if (isAdd) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    protected void setVisibilityImageBack(int visibility) {
        mImgBack.setVisibility(visibility);
    }
}
