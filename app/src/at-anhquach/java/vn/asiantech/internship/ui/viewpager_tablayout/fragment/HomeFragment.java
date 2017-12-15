package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Dictionary;
import vn.asiantech.internship.ui.viewpager_tablayout.RotationPageTransformer;
import vn.asiantech.internship.ui.viewpager_tablayout.adapter.DictionaryAdapter;

public class HomeFragment extends Fragment {
    public static List<Dictionary> mDictionaryList = new ArrayList<>();
    ViewPager viewPager;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initData();
        initAdapter();
        setAnimation();
        return view;
    }

    private void initViews(View view) {
        viewPager = view.findViewById(R.id.viewPagerHomeFragment);
    }

    private void setAnimation() {
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(false, new RotationPageTransformer(160));
        viewPager.setPageMargin(-getResources().getDimensionPixelOffset(R.dimen.sile_padding) * 2);
        viewPager.setClipChildren(false);
    }

    private void initData() {
        mDictionaryList.add(new Dictionary("Bear", "Gấu", R.drawable.img_dictionary_bear));
        mDictionaryList.add(new Dictionary("Bee", "Ong", R.drawable.img_dictionary_bee));
        mDictionaryList.add(new Dictionary("Elk", "Nai", R.drawable.img_dictionary_elk));
    }

    private void initAdapter() {
        viewPager.setAdapter(new DictionaryAdapter(getFragmentManager(), mDictionaryList));
    }
}
