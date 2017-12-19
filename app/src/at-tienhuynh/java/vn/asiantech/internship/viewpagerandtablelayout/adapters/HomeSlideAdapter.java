package vn.asiantech.internship.viewpagerandtablelayout.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.viewpagerandtablelayout.models.SlideHome;
import vn.asiantech.internship.viewpagerandtablelayout.ui.HomeSlideFragment;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public class HomeSlideAdapter extends FragmentStatePagerAdapter {
    private List<SlideHome> mHomeSlideLists = new ArrayList<>();

    public HomeSlideAdapter(FragmentManager fm, List<SlideHome> homeSlideList) {
        super(fm);
        mHomeSlideLists = homeSlideList;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeSlideFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mHomeSlideLists.size();
    }
}
