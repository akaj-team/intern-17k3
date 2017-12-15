package vn.asiantech.internship.viewpagerandtablelayout.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.viewpagerandtablelayout.models.SlideHomeFragment;
import vn.asiantech.internship.viewpagerandtablelayout.ui.HomeSlideFragment;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public class HomeSlideAdapter extends FragmentStatePagerAdapter {
    private List<SlideHomeFragment> mHomeSlideList = new ArrayList<>();
    public static int positionItem;

    public HomeSlideAdapter(FragmentManager fm, List<SlideHomeFragment> homeSlideList) {
        super(fm);
        mHomeSlideList = homeSlideList;
    }

    @Override
    public Fragment getItem(int position) {
        positionItem = position;
        return new HomeSlideFragment();


    }

    @Override
    public int getCount() {
        return mHomeSlideList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object != null && ((Fragment) object).getView() == view;
    }
}
