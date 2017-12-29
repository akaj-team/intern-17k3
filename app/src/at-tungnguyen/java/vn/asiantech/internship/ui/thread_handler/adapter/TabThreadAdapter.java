package vn.asiantech.internship.ui.thread_handler.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.internship.ui.thread_handler.ui.thread.DownloadFragment;
import vn.asiantech.internship.ui.thread_handler.ui.thread.ViewFragment;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 19/12/2017.
 */
public class TabThreadAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"DownLoad", "View"};
    private static final int ITEM_DOWNLOAD = 0;
    private static final int ITEM_VIEW = 1;
    private ViewFragment mViewFragment = new ViewFragment();
    private DownloadFragment mDownloadFragment = new DownloadFragment();

    public TabThreadAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ITEM_DOWNLOAD:
                return mDownloadFragment;
            case ITEM_VIEW:
                return mViewFragment;
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
