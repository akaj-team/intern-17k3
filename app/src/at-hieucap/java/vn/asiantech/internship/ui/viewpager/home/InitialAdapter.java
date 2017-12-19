package vn.asiantech.internship.ui.viewpager.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Create Initial Activity
 */
public class InitialAdapter extends PagerAdapter {
    private List<String> mListFragment;
    private LayoutInflater mLayoutInflater;

    InitialAdapter(Context context, List<String> fragment) {
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
        View myTextLayout = mLayoutInflater.inflate(R.layout.item_fragment_initial, view, false);
        TextView tvContext = myTextLayout.findViewById(R.id.tvText);
        tvContext.setText(mListFragment.get(position));
        view.addView(myTextLayout, 0);
        return myTextLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
