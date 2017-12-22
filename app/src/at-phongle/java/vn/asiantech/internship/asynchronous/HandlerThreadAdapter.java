package vn.asiantech.internship.asynchronous;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by phongle on 22/12/2560.
 * Adapter ViewPager Thread Handler
 */

public class HandlerThreadAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 2;

    HandlerThreadAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new DownloadImageFragment();
                break;
            case 1:
                fragment = new ShowImageFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = "DOWNLOAD";
                break;
            case 1:
                title = "SHOW";
                break;
            default:
                return null;
        }
        return title;
    }
}
