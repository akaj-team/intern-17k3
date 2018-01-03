package vn.asiantech.internship.ui.viewpager.information;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.models.CardEnglish;

/**
 * Create Home Adapter
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    private List<CardEnglish> mCardEnglish = new ArrayList<>();

    HomeAdapter(MenuFragment context, List<CardEnglish> mListCardEnglish) {
        super(context.getChildFragmentManager());
        mCardEnglish = mListCardEnglish;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mCardEnglish.size();
    }
}
