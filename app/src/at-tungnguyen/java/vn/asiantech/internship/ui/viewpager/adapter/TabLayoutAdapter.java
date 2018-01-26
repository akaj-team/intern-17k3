package vn.asiantech.internship.ui.viewpager.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.ui.viewpager.ui.AnotherFragment;
import vn.asiantech.internship.ui.viewpager.ui.HomeFragment;
import vn.asiantech.internship.ui.viewpager.ui.InfoFragment;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 14/12/2017.
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Home", "Info","Another"};

    public TabLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new InfoFragment();
            case 2 :
                return new AnotherFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
