package vn.asiantech.internship.viewpager.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;

public class ViewPagerAdapter extends PagerAdapter {
    private List<String> mListFragment;
    private LayoutInflater mLayoutInflater;

    ViewPagerAdapter(Context context, List<String> fragment) {
        mListFragment = fragment;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = mLayoutInflater.inflate(R.layout.fragment_item, view, false);
        TextView tvContext = myImageLayout.findViewById(R.id.tvText);
        tvContext.setText(mListFragment.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
