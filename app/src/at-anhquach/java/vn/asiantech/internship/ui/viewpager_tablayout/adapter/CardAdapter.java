package vn.asiantech.internship.ui.viewpager_tablayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.asiantech.internship.model.Card;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.CardFragment;

public class CardAdapter extends FragmentStatePagerAdapter {
    private List<Card> mCardList;

    public CardAdapter(FragmentManager fm, List<Card> dictionaries) {
        super(fm);
        this.mCardList = dictionaries;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mCardList.size()) {
            return CardFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mCardList.size();
    }
}
