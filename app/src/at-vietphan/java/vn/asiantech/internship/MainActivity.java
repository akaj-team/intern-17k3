package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        replaceFragment(IndexFragment.newInstance(), false);
        mImgBack = findViewById(R.id.imgBack);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
            }
        });
    }
git
    protected void replaceFragment(Fragment fragment, boolean isAdd) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frContent, fragment);
        if (isAdd) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public void setVisibilityBackImageView(int visible) {
         mImgBack.setVisibility(visible);
    }
}
