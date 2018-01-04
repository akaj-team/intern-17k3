package vn.asiantech.internship.ui.asynchronous.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.ui.asynchronous.fragments.OneFragment;
import vn.asiantech.internship.ui.asynchronous.fragments.TwoFragment;

/**
 * Created by vietphan on 20/12/2017.
 * Class ThreadHandleAdapter
 */
public class ThreadHandleAdapter extends FragmentPagerAdapter {
    private static final int NUM_TABS = 2;
    private OneFragment oneFragment = new OneFragment();
    private TwoFragment twoFragment = new TwoFragment();

    public ThreadHandleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return oneFragment;
            case 1:
                return twoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FRAGMENT ONE";
            default:
                return "FRAGMENT TWO";
        }
    }
}
