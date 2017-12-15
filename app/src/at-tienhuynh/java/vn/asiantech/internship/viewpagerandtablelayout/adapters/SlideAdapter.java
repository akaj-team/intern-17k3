package vn.asiantech.internship.viewpagerandtablelayout.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created at 2017
 * Created by jackty on 13/12/2017.
 */
public class SlideAdapter extends PagerAdapter {

    private List<String> mListStep;
    private LayoutInflater mLayoutInflater;

    public SlideAdapter(Context context, List<String> steps) {
        mListStep = steps;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mListStep.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = mLayoutInflater.inflate(R.layout.item_slide_layout, view, false);
        TextView tvStepSlide = myImageLayout
                .findViewById(R.id.tvStepSlide);
        tvStepSlide.setText(mListStep.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
