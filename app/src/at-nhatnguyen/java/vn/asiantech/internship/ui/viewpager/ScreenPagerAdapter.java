package vn.asiantech.internship.ui.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Screen;

/**
 * Created by hoangnhat on 14/12/2017.
 * Class Adapter for viewpagerActivity
 */

public class ScreenPagerAdapter extends PagerAdapter {
    private List<Screen> mScreens;
    private Context mContext;

    ScreenPagerAdapter(List<Screen> screens, Context context) {
        mScreens = screens;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mScreens.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_before_screen, container, false);
        initViews(view, position);
        container.addView(view);
        return view;
    }

    private void initViews(View view, int position) {
        TextView tvScreenName = view.findViewById(R.id.tvScreenName);
        LinearLayout llScreen = view.findViewById(R.id.llScreen);
        tvScreenName.setText(mScreens.get(position).getContent());
        llScreen.setBackgroundColor(mScreens.get(position).getColor());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
