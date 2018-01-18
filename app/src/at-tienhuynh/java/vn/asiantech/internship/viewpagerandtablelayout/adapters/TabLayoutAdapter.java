package vn.asiantech.internship.viewpagerandtablelayout.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.viewpagerandtablelayout.ui.AnotherFragment;
import vn.asiantech.internship.viewpagerandtablelayout.ui.HomeFragment;
import vn.asiantech.internship.viewpagerandtablelayout.ui.InfoFragment;

/**
 * Created at 2017
 * Created by jackty on 13/12/2017.
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_INFO = 1;
    private static final int FRAGMENT_ANOTHER = 2;
    private String[] mFragment = {"Home", "Info", "Another"};

    public TabLayoutAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_HOME:
                return new HomeFragment();
            case FRAGMENT_INFO:
                return new InfoFragment();
            case FRAGMENT_ANOTHER:
                return new AnotherFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragment[position];
    }
}
