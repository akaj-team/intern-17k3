package vn.asiantech.internship.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.viewpager.home.HomeFragment;
import vn.asiantech.internship.viewpager.info.InformationFragment;

/**
 * Created by phongle on 14/12/2560.
 * MainAdapter
 */
public class MainAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 2;

    MainAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new InformationFragment();
                break;
            default:
                return null;
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
                title = "HOME";
                break;
            case 1:
                title = "INFORMATION";
                break;
            default:
                return null;
        }
        return title;
    }
}
