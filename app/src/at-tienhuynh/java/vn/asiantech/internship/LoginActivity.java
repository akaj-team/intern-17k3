package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        mImgBack.setOnClickListener(this);
        replaceFragment(LoginFragment.newInstance(), false);
    }

    protected void replaceFragment(Fragment fragment, boolean isAdd) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frLogin, fragment);
        if (isAdd) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    protected void setVisiblilyImageBack(int visiblity) {
        mImgBack.setVisibility(visiblity);
    }

    @Override
    public void onClick(View view) {
        getSupportFragmentManager().popBackStack();
    }
}
