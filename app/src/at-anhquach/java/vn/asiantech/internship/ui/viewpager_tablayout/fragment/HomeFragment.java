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

public class HomeFragment extends Fragment {
    public static List<Card> mCardList = new ArrayList<>();
    private ViewPager viewPager;

    public HomeFragment newInstance() {
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
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false, new RotationPageTransformer(160));
        viewPager.setPageMargin(-getResources().getDimensionPixelOffset(R.dimen.sile_padding) * 2);
        viewPager.setClipChildren(false);
    }

    private void initData() {
        mCardList.add(new Card(1,"Bear","Gau", R.drawable.img_card_bear));
        mCardList.add(new Card(2,"Bee", "Ong", R.drawable.img_card_bee));
        mCardList.add(new Card(3,"Elk", "Nai", R.drawable.img_card_elk));
        mCardList.add(new Card(4,"Frog", "", R.drawable.img_card_frog));
        mCardList.add(new Card(5,"Girafe", "Hươu cao ", R.drawable.img_card_girafe));
        mCardList.add(new Card(6,"Goat", "Dê", R.drawable.img_card_goat));
        mCardList.add(new Card(7,"Hippo", "Hà ma", R.drawable.img_card_hippo));
        mCardList.add(new Card(8,"Kangaroo", "Chuột ", R.drawable.img_card_kangaroo));
        mCardList.add(new Card(9,"Leopard", "Sư tu", R.drawable.img_card_leopard));
    }

    private void initAdapter() {
        viewPager.setAdapter(new CardAdapter(getFragmentManager(), mCardList));
    }
}
