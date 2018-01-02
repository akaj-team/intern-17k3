package vn.asiantech.internship.asynchronous;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 19/12/2560.
 * Activity Handler Thread
 */
public class HandlerThreadActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Bitmap mBitmap = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        initViews();
        HandlerThreadAdapter handlerThreadAdapter = new HandlerThreadAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(handlerThreadAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerThreadHandler);
        mTabLayout = findViewById(R.id.tabLayoutThreadHandler);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(1);
            }
        });
    }
}
