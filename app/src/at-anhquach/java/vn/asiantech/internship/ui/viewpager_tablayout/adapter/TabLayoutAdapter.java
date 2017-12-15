package vn.asiantech.internship.ui.viewpager_tablayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mFragmentTittles;

    public TabLayoutAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> fragmentTittles) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mFragmentTittles = fragmentTittles;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTittles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTittles.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
