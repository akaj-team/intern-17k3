package vn.asiantech.internship.ui.viewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 14/12/2017.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<String> mStrings = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(List<String> mStrings, Context context) {
        this.mStrings = mStrings;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.item_slide,container,false);
        TextView tvStepSlide = view.findViewById(R.id.tvSlide);
        tvStepSlide.setText(mStrings.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
