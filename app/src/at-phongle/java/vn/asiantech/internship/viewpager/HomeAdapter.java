package vn.asiantech.internship.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.asiantech.internship.models.Dictionary;

/**
 * Created by phongle on 14/12/2560.
 * HomeAdapter
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    private List<Dictionary> mDictionaryList;

    HomeAdapter(FragmentManager fm, List<Dictionary> dictionaryList) {
        super(fm);
        mDictionaryList = dictionaryList;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDictionaryFragment.init(position);
    }

    @Override
    public int getCount() {
        return mDictionaryList.size();
    }
}
