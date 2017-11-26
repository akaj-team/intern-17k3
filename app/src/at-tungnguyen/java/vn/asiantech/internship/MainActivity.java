
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
        mImgBack = findViewById(R.id.imgBack);
        replace(new LoginFragment(), false);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void replace(Fragment fragment, boolean isAdd) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frContainer, fragment);
        if (isAdd) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public ImageView getBack() {
        return mImgBack;
    }

}