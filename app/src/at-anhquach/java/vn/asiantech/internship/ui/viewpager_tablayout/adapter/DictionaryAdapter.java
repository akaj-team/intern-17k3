package vn.asiantech.internship.ui.viewpager_tablayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.asiantech.internship.model.Dictionary;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.DictionaryFragment;

public class DictionaryAdapter extends FragmentStatePagerAdapter {
    private List<Dictionary> mDictionaryList;

    public DictionaryAdapter(FragmentManager fm, List<Dictionary> dictionaries) {
        super(fm);
        this.mDictionaryList = dictionaries;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mDictionaryList.size()) {
            return DictionaryFragment.newInstance(position);
        } else
            return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mDictionaryList.size();
    }
}
