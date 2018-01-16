package vn.asiantech.internship.ui.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.ui.viewpager.fragments.BroadcastFragment;
import vn.asiantech.internship.ui.viewpager.fragments.HomeFragment;
import vn.asiantech.internship.ui.viewpager.fragments.InformationFragment;

/**
 * Created by vietphan on 13/12/2017.
 * Class TabLayoutAdapter
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {
    private static final int NUM_TABS = 3;

    public TabLayoutAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new InformationFragment();
            default:
                return new BroadcastFragment();
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
                return "HOME";
            case 1:
                return "INFO";
            default:
                return "BROADCAST";
        }
    }
}
