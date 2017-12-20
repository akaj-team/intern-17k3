package vn.asiantech.internship.ui.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import vn.asiantech.internship.models.Vocabulary;
import vn.asiantech.internship.ui.viewpager.fragments.HomeSliderFragment;

/**
 * Created by vietphan on 13/12/2017.
 * Class TabLayoutAdapter
 */
public class VocabularyAdapter extends FragmentStatePagerAdapter {
    private List<Vocabulary> mVocabularies;

    public VocabularyAdapter(FragmentManager fm, List<Vocabulary> vocabularies) {
        super(fm);
        this.mVocabularies = vocabularies;
    }

    @Override
    public Fragment getItem(int position) {
        HomeSliderFragment homeSliderFragment = new HomeSliderFragment();
        homeSliderFragment.setVocabulary(mVocabularies.get(position));
        return homeSliderFragment;
    }

    @Override
    public int getCount() {
        return mVocabularies.size();
    }
}
