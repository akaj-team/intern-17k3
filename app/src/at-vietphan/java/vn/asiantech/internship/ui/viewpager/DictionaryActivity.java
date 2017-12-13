package vn.asiantech.internship.ui.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.fragments.HomeFragment;
import vn.asiantech.internship.ui.viewpager.fragments.InformationFragment;

/**
 * Created by vietphan on 13/12/2017.
 * Class DictionaryActivity
 */
public class DictionaryActivity extends AppCompatActivity{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private DictionaryAdapter mDictionaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        mViewPager = findViewById(R.id.viewPagerDictionary);
        setupViewPager(mViewPager);
        mTabLayout = findViewById(R.id.tabLayoutDictionary);
//        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mDictionaryAdapter = new DictionaryAdapter(getSupportFragmentManager());
        mDictionaryAdapter.addFragment(new HomeFragment(), "HOME");
        mDictionaryAdapter.addFragment(new InformationFragment(), "INFO");
        viewPager.setAdapter(mDictionaryAdapter);
    }
}
