package vn.asiantech.internship.viewpagerandtablelayout.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.Utils.InitData;
import vn.asiantech.internship.viewpagerandtablelayout.Utils.RotationPageTransformer;
import vn.asiantech.internship.viewpagerandtablelayout.adapters.HomeSlideAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private View mView;
    private ViewPager mViewPagerHomeFragment;

    public HomeFragment() {
        //No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        initAdapters();
        return mView;
    }

    /**
     * Init Views
     */
    private void initViews() {
        mViewPagerHomeFragment = mView.findViewById(R.id.viewPagerHomeFragment);
    }

    /**
     * Init Adapters
     */
    private void initAdapters() {
        HomeSlideAdapter homeSlideAdapter = new HomeSlideAdapter(getFragmentManager(), InitData.listDictionary());
        mViewPagerHomeFragment.setAdapter(homeSlideAdapter);
        mViewPagerHomeFragment.setPageTransformer(true, new RotationPageTransformer(135));
    }

}
