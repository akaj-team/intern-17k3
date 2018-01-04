package vn.asiantech.internship.ui.viewpager.information;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.CardEnglish;
import vn.asiantech.internship.ui.viewpager.home.RotationPageTransformer;

/**
 * Create Menu Fragment
 */
public class MenuFragment extends Fragment {
    public static final List<CardEnglish> mListCardEnglish = new ArrayList<>();
    private static final int CURRENT_PAGE = 0;
    private ViewPager mViewPager;
    private HomeAdapter mAdapterHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_menu, container, false);
        mViewPager = view.findViewById(R.id.viewPagerHome);
        initData();
        initAdapter();
        mViewPager.setAdapter(mAdapterHome);
        mViewPager.setCurrentItem(CURRENT_PAGE);
        return view;
    }

    private void initAdapter() {
        mAdapterHome = new HomeAdapter(MenuFragment.this, mListCardEnglish);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new RotationPageTransformer(150));
        mViewPager.setPageMargin(-(getResources().getDimensionPixelOffset(R.dimen.cardMargin)) * 2);
        mViewPager.setClipChildren(false);
    }

    private void initData() {
        mListCardEnglish.add(new CardEnglish("Bear", R.drawable.ic_bear, "Gấu"));
        mListCardEnglish.add(new CardEnglish("Bee", R.drawable.ic_bee, "Ong"));
        mListCardEnglish.add(new CardEnglish("Elk", R.drawable.ic_elk, "Nai"));
        mListCardEnglish.add(new CardEnglish("Frog", R.drawable.ic_frog, "Ếch"));
        mListCardEnglish.add(new CardEnglish("Giraffe", R.drawable.ic_girafe, "Hươu cao cổ"));
        mListCardEnglish.add(new CardEnglish("Kangaroo", R.drawable.ic_kangaroo, "Kangaroo"));
    }
}
