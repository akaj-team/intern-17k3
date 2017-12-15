package vn.asiantech.internship.ui.viewpager.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Vocabulary;
import vn.asiantech.internship.ui.viewpager.adapter.VocabularyAdapter;
import vn.asiantech.internship.utils.RotationPageTransformer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager mViewPagerVocabulary;
    private List<Vocabulary> mVocabularies;
    private VocabularyAdapter mVocabularyAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initData();
        return view;
    }

    private void initViews(View view) {
        mViewPagerVocabulary = view.findViewById(R.id.viewPagerVocabulary);
        mVocabularies = new ArrayList<>();
    }

    private void initData() {
        mVocabularyAdapter = new VocabularyAdapter(getFragmentManager(), mVocabularies);
        mVocabularies.add(new Vocabulary("Bear", R.drawable.bear, "Gấu"));
        mVocabularies.add(new Vocabulary("Bee", R.drawable.bee, "Ong"));
        mVocabularies.add(new Vocabulary("Elk", R.drawable.elk, "Nai"));
        mVocabularies.add(new Vocabulary("Frog", R.drawable.frog, "Ếch"));
        mVocabularies.add(new Vocabulary("Giraffe", R.drawable.giraffe, "Hưu cao cổ"));
        mVocabularies.add(new Vocabulary("Goat", R.drawable.goat, "Dê"));
        mVocabularies.add(new Vocabulary("Hippo", R.drawable.hippo, "Tê giác"));
        mVocabularies.add(new Vocabulary("Kangaroo", R.drawable.kangaroo, "Kangraroo"));
        mVocabularies.add(new Vocabulary("Leopard", R.drawable.leopard, "Báo"));
        mVocabularies.add(new Vocabulary("Lion", R.drawable.lion, "Sư tử"));
        mVocabularies.add(new Vocabulary("llama", R.drawable.llama, "???"));
        mVocabularies.add(new Vocabulary("Monkey", R.drawable.monkey, "Khỉ"));
        mVocabularies.add(new Vocabulary("Moose", R.drawable.moose, "Chuột"));
        mVocabularies.add(new Vocabulary("Ostrich", R.drawable.ostrich, "???"));
        mVocabularies.add(new Vocabulary("Owl", R.drawable.owl, "Cú mèo"));
        mVocabularies.add(new Vocabulary("Panda", R.drawable.panda, "Gấu trúc"));
        mVocabularies.add(new Vocabulary("Peacock", R.drawable.peacock, "Công"));
        mVocabularies.add(new Vocabulary("Penguin", R.drawable.penguin, "Chim cánh cụt"));
        mVocabularies.add(new Vocabulary("Rhino", R.drawable.rhino, "Tê giác ???"));
        mVocabularies.add(new Vocabulary("Squirrel", R.drawable.squirrel, "Sóc"));
        mVocabularies.add(new Vocabulary("walrus", R.drawable.walrus, "Hà mã"));
        mVocabularies.add(new Vocabulary("Weasel", R.drawable.weasel, "Chuột túi"));
        mVocabularies.add(new Vocabulary("yak", R.drawable.yak, "Linh dương đầu bò"));
        mVocabularies.add(new Vocabulary("Zebra", R.drawable.zebra, "Ngựa vằn"));

        String[] firstLanguage;
        mViewPagerVocabulary.setAdapter(mVocabularyAdapter);
        mViewPagerVocabulary.setPageTransformer(true, new RotationPageTransformer(160));
        mViewPagerVocabulary.setOffscreenPageLimit(mVocabularyAdapter.getCount());
        mViewPagerVocabulary.setPageMargin(-50);
        mViewPagerVocabulary.setClipChildren(false);
    }
}
