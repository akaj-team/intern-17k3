package vn.asiantech.internship.threadandhandler.dowloadimage.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.threadandhandler.dowloadimage.ui.DownLoadImageFragment;
import vn.asiantech.internship.threadandhandler.dowloadimage.ui.ViewImageFragment;

/**
 * Created at 2017
 * Created by jackty on 19/12/2017.
 */
public class TabAdapter extends FragmentPagerAdapter {
    private static final int FRAGMENT_ONE = 0;
    private static final int FRAGMENT_TW0 = 1;
    private String mNameFragment[] = {"Download", "View"};
    private DownLoadImageFragment mDownLoadImageFragment = new DownLoadImageFragment();
    private ViewImageFragment mViewImageFragment = new ViewImageFragment();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_ONE:
                return mDownLoadImageFragment;
            case FRAGMENT_TW0:
                return mViewImageFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNameFragment.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNameFragment[position];
    }
}
