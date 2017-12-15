package vn.asiantech.internship.ui.viewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Vocabulary;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<Vocabulary> mVocabularies;
    private ViewPager mViewPagerHome;

    public HomeFragment() {
        //No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initAdapter();
        return view;
    }

    private void initViews(View view) {
        mViewPagerHome = view.findViewById(R.id.viewPagerHome);
    }

    private void initAdapter() {
        VocabularyAdapter mVocabularyAdapter = new VocabularyAdapter(getFragmentManager(), mVocabularies);
        mViewPagerHome.setAdapter(mVocabularyAdapter);
        mViewPagerHome.setPageTransformer(true, new RotationPageTransformer(150, 1));
    }

    private void initData() {
        mVocabularies = new ArrayList<>();
        mVocabularies.add(new Vocabulary("Bear", R.drawable.ic_bear, "Gau"));
        mVocabularies.add(new Vocabulary("Bee", R.drawable.ic_bee, "Buom"));
        mVocabularies.add(new Vocabulary("Elk", R.drawable.ic_elk, "Nai"));
        mVocabularies.add(new Vocabulary("Frog", R.drawable.ic_frog, "Ech"));
        mVocabularies.add(new Vocabulary("Girafe", R.drawable.ic_girafe, "Huu cao co"));
        mVocabularies.add(new Vocabulary("Goat", R.drawable.ic_goat, "De"));
        mVocabularies.add(new Vocabulary("Hippo", R.drawable.ic_hippo, "Ha ma"));
        mVocabularies.add(new Vocabulary("Launcher", R.drawable.ic_launcher, "Chim"));
    }
}
