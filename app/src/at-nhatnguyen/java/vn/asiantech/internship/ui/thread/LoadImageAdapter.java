package vn.asiantech.internship.ui.thread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.ui.thread.fragment.OneFragment;
import vn.asiantech.internship.ui.thread.fragment.TwoFragment;

/**
 * Created by hoangnhat on 20/12/2017.
 * Adapter for LoadImageActivity
 */
public class LoadImageAdapter extends FragmentPagerAdapter {
    private OneFragment oneFragment = OneFragment.newInstance();
    private TwoFragment twoFragment = TwoFragment.newInstance();

    LoadImageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return oneFragment;
        }
        if (position == 1) {
            return twoFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "One";
        } else {
            return "Two";
        }
    }
}
