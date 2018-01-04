package vn.asiantech.internship.asynchronous;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by anh.quach on 12/19/17.
 * adapter tab layout
 */
public class TabThreadAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mFragmentTittles;

    TabThreadAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> fragmentTittles) {
        super(fm);
        this.mFragments = fragmentList;
        this.mFragmentTittles = fragmentTittles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    void addFrag(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTittles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTittles.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
