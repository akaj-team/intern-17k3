package vn.asiantech.internship.viewpager.tutorial;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 14/12/2560.
 * Adapter view pager How o use
 */
public class GuideAdapter extends PagerAdapter {
    private Context mContext;
    private String mListStep[];
    private int mListColor[];

    GuideAdapter(Context context, String[] listStep, int[] listColor) {
        mContext = context;
        mListStep = listStep;
        mListColor = listColor;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.step_how_to_used, container, false);
        LinearLayout llStepToUse = view.findViewById(R.id.llStepToUse);
        TextView tvStepToUse = view.findViewById(R.id.tvStepToUse);
        llStepToUse.setBackgroundResource(mListColor[position]);
        tvStepToUse.setText(mListStep[position]);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
