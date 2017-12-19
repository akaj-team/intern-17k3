package vn.asiantech.internship.viewpager.home.card;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.asiantech.internship.models.Dictionary;

/**
 * Created by phongle on 14/12/2560.
 * HomeAdapter
 */
public class ItemCardAdapter extends FragmentStatePagerAdapter {
    private List<Dictionary> mDictionaryList;

    public ItemCardAdapter(FragmentManager fm, List<Dictionary> dictionaryList) {
        super(fm);
        mDictionaryList = dictionaryList;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemCardFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mDictionaryList.size();
    }
}
