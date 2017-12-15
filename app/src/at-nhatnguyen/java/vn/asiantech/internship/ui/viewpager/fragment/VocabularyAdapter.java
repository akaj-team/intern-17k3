package vn.asiantech.internship.ui.viewpager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.asiantech.internship.models.Vocabulary;

/**
 * Created by hoangnhat on 14/12/2017.
 * Adapter for HomeFrament
 */
public class VocabularyAdapter extends FragmentStatePagerAdapter {
    private List<Vocabulary> mVocabularies;

    VocabularyAdapter(FragmentManager fm, List<Vocabulary> vocabularies) {
        super(fm);
        mVocabularies = vocabularies;
    }

    @Override
    public Fragment getItem(int position) {
        HomeItemFragment homeItemFragment = new HomeItemFragment();
        homeItemFragment.setmVocabulary(mVocabularies.get(position));
        return homeItemFragment;
    }

    @Override
    public int getCount() {
        return mVocabularies.size();
    }
}
