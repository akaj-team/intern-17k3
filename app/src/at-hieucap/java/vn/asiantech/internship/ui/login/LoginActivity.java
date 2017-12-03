package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import vn.asiantech.internship.R;

public class LoginActivity extends AppCompatActivity {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mImgBack = findViewById(R.id.imgBack);
        mImgBack.setImageDrawable(getResources().getDrawable(R.drawable.selector_event_back));
        mImgBack.setEnabled(false); // Can't press back button
        mImgBack.setSelected(false);
        // Add first fragment
        replaceFragment(LoginFragment.getInstance(), false);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(LoginFragment.getInstance(), false);
                onBackPressed();
            }
        });
    }

    protected void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frContain, fragment);
        if (addBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    protected void updateBackButton(boolean show) {
        mImgBack.setEnabled(show);
        mImgBack.setSelected(show);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateBackButton(false);
    }
}
