package vn.asiantech.internship.ui.thread_handler.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 19/12/2017.
 */
public class TabThreadAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"DownLoad", "View"};
    private List<Fragment> fragmentList;

    public TabThreadAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
        super(supportFragmentManager);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
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
