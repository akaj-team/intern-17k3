package vn.asiantech.internship.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 14/12/2560.
 */

public class HowToUsedAdapter extends PagerAdapter {
    private List<View> mListViews = new ArrayList<>();
    private LayoutInflater layoutInflater;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.view_step_to_used, container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    private void addView(){

    }
}
