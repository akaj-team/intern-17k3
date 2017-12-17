package vn.asiantech.internship.viewpager.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpager.adapter.SlideHomeAdapter;
import vn.asiantech.internship.viewpager.model.Animal;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager mViewPager;
    private ArrayList<Animal> mAnimalArrayList = new ArrayList<>();

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = view.findViewById(R.id.viewPagerHome);
        initData();
        return view;
    }

    public void initData() {
        SlideHomeAdapter slideHomeAdapter = new SlideHomeAdapter(getFragmentManager(), mAnimalArrayList);
        mAnimalArrayList.add(new Animal("Monkey", R.drawable.monkey, "Con khỉ"));
        mAnimalArrayList.add(new Animal("Panda", R.drawable.panda, "Gấu trúc"));
        mAnimalArrayList.add(new Animal("Bear", R.drawable.bear, "Con gấu"));
        mViewPager.setPageTransformer(true, new RotationPageTransformer(165));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setClipChildren(false);
        mViewPager.setPageMargin(-getResources().getDimensionPixelOffset(R.dimen.slide_padding) * 2);
        mViewPager.setAdapter(slideHomeAdapter);
    }
}
