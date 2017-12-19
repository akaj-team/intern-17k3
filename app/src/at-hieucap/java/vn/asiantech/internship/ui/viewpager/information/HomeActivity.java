package vn.asiantech.internship.ui.viewpager.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Create Home Activity
 */
public class HomeActivity extends AppCompatActivity {
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewPager viewPager = findViewById(R.id.viewPagerActivityHome);
        mTabLayout = findViewById(R.id.tlContain);
        setupViewPager(viewPager);
    }

    public void setupViewPager(ViewPager mViewPager) {
        InformationAdapter adapter = new InformationAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuFragment(), "Menu");
        adapter.addFragment(new InformationFragment(), "Information");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
    }
}
