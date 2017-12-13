package vn.asiantech.internship.ui.viewpager;

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
 * Class PageAdapter
 */
public class PageAdapter extends PagerAdapter implements View.OnClickListener {
    private List<Step> mSteps;
    private TextView mTvSkip;
    private OnItemClickListener mOnItemClickListener;

    public PageAdapter(List<Step> steps, OnItemClickListener onItemClickListener) {
        this.mSteps = steps;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View viewContent = inflater.inflate(R.layout.item_view_step, container, false);
        viewContent.setBackgroundColor(mSteps.get(position).getColor());
        TextView textView = viewContent.findViewById(R.id.tvContentView);
        textView.setText(String.valueOf(mSteps.get(position).getName()));
        mTvSkip = viewContent.findViewById(R.id.tvSkip);
        mTvSkip.setOnClickListener(this);
        container.addView(viewContent);
        return viewContent;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onSkipClick();
        }
    }

    public interface OnItemClickListener {
        void onSkipClick();
    }
}
