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
import vn.asiantech.internship.model.Card;
import vn.asiantech.internship.ui.viewpager_tablayout.RotationPageTransformer;
import vn.asiantech.internship.ui.viewpager_tablayout.adapter.CardAdapter;

public final class HomeFragment extends Fragment {
    public static List<Card> mCardList = new ArrayList<>();
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
        mCardList.add(new Card("Bear", "Gấu", R.drawable.img_dictionary_bear));
        mCardList.add(new Card("Bee", "Ong", R.drawable.img_dictionary_bee));
        mCardList.add(new Card("Elk", "Nai", R.drawable.img_dictionary_elk));
    }

    private void initAdapter() {
        viewPager.setAdapter(new CardAdapter(getFragmentManager(), mCardList));
    }
}
