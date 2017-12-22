package vn.asiantech.internship.ui.asynchronous.activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.asynchronous.adapters.ThreadHandleAdapter;
import vn.asiantech.internship.ui.asynchronous.fragments.TwoFragment;

/**
 * Created by vietphan on 20/12/2017.
 * Class ThreadHandleActivity
 */
public class ThreadHandleActivity extends AppCompatActivity {
    public ThreadHandleAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handle_countdown_timer);
        initViews();
        setUpViewPager();
    }

    private void initViews() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }

    private void setUpViewPager() {
        mAdapter = new ThreadHandleAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void sendMessageToTwoFragment(Bitmap bitmap) {
        TwoFragment twoFragment = (TwoFragment) mAdapter.getItem(1);
        if (twoFragment != null) {
            twoFragment.receiveMessageFromOneFragment(bitmap);
        } else {
            Toast.makeText(this, R.string.found_fragment, Toast.LENGTH_SHORT).show();
        }
    }
}
