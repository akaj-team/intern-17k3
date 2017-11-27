package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import vn.asiantech.internship.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        mImgBack.setOnClickListener(this);
        addFragment(LoginFragment.getInstance(), false);
    }

    protected void addFragment(Fragment fragment, boolean addBackStack) {
        mImgBack.setSelected(addBackStack);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frContainer, fragment);
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        addFragment(new LoginFragment(), false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mImgBack.setSelected(false);
        }
        return super.onKeyDown(keyCode, event);
    }
}
