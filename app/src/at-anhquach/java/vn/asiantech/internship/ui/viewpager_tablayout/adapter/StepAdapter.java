package vn.asiantech.internship.ui.viewpager_tablayout.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class StepAdapter extends PagerAdapter {
    private Context mContent;
    private String[] mStrs;
    private int[] mColors;

    public StepAdapter(Context context, String[] str, int[] colors) {
        mContent = context;
        mStrs = str;
        mColors = colors;
    }

    @Override
    public int getCount() {
        return mStrs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContent);
        View view = mLayoutInflater.inflate(R.layout.viewpager_step, viewGroup, false);
        TextView tvSlide = view.findViewById(R.id.tvSlide);
        FrameLayout fmContent = view.findViewById(R.id.fmContent);
        fmContent.setBackgroundResource(mColors[position]);
        tvSlide.setText(mStrs[position]);
        viewGroup.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
