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
    private Boolean mCheckBackStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        mImgBack.setOnClickListener(this);
        replaceFragment(LoginFragment.getInstance(), false);
        mImgBack.setSelected(mCheckBackStatus);
    }

    protected void replaceFragment(Fragment fragment, boolean addBackStack) {
        mCheckBackStatus = addBackStack;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frContainer, fragment);
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    protected void setStatusBack() {
        mImgBack.setSelected(mCheckBackStatus);
    }

    @Override
    public void onClick(View v) {
        replaceFragment(new LoginFragment(), false);
        setStatusBack();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mImgBack.setSelected(false);
        }
        return super.onKeyDown(keyCode, event);
    }
}
