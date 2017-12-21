package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiboo on 20/12/2017.
 * Create fragment adapter
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> fragmentTittles) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mFragmentTitleList = fragmentTittles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

