package vn.asiantech.internship.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Dictionary;

/**
 * Created by phongle on 14/12/2560.
 * Fragment Home
 */
public class HomeFragment extends Fragment {
    public static List<Dictionary> mDictionaryList = new ArrayList<>();
    private ViewPager mViewPagerHome;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPagerHome = view.findViewById(R.id.viewPagerHome);
        initData();
        initAdapter();
        return view;
    }

    private void initData() {
        mDictionaryList.add(new Dictionary("Sư tử", "Lion", R.drawable.bg_lion));
        mDictionaryList.add(new Dictionary("Gấu", "Bear", R.drawable.bg_bear));
        mDictionaryList.add(new Dictionary("Báo", "Leopard", R.drawable.bg_leopard));
        mDictionaryList.add(new Dictionary("Ong", "Beo", R.drawable.bg_bee));
        mDictionaryList.add(new Dictionary("Ếch", "Frog", R.drawable.bg_frog));
        mDictionaryList.add(new Dictionary("Nai", "Elk", R.drawable.bg_elk));
    }

    private void initAdapter() {
        HomeAdapter homeAdapter = new HomeAdapter(getFragmentManager(), mDictionaryList);
        mViewPagerHome.setAdapter(homeAdapter);
        mViewPagerHome.setPageTransformer(true, new RotationPageTransformer(150, 1));
        mViewPagerHome.setPageMargin(-(getResources().getDimensionPixelOffset(R.dimen.card_padding)) * 2);
        mViewPagerHome.setOffscreenPageLimit(3);
    }
}
