package vn.asiantech.internship.ui.viewpager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hoangnhat on 14/12/2017.
 * Adapter for ViewPagerMainActivity
 */
public class FragmentMainAdapter extends FragmentPagerAdapter {
    private static final int POSITION_HOME = 0;
    private static final int POSITION_INFORMATION = 1;
    private static final int POSITION_ANOTHER = 2;

    public FragmentMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case POSITION_HOME:
                fragment = new HomeFragment();
                break;
            case POSITION_INFORMATION:
                fragment = new InforFragment();
                break;
            case POSITION_ANOTHER:
                fragment = new AnotherFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case POSITION_HOME:
                title = "Home";
                break;
            case POSITION_INFORMATION:
                title = "Infor";
                break;
            case POSITION_ANOTHER:
                title = "Another";
        }
        return title;
    }
}
