package vn.asiantech.internship.ui.viewpager.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Step;

/**
 * Created by vietphan on 13/12/2017.
 * Class SliderStepAdapter
 */
public class SliderStepAdapter extends PagerAdapter {
    private List<Step> mSteps;

    public SliderStepAdapter(List<Step> steps) {
        this.mSteps = steps;
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View viewContent = inflater.inflate(R.layout.item_slider_step, container, false);
        TextView textView = viewContent.findViewById(R.id.tvContentView);
        textView.setTextColor(mSteps.get(position).getColor());
        textView.setText(String.valueOf(mSteps.get(position).getName()));
        container.addView(viewContent);
        return viewContent;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
