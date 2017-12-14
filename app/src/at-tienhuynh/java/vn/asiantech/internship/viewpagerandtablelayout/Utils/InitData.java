package vn.asiantech.internship.viewpagerandtablelayout.Utils;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.models.SlideHomeFragment;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public final class InitData {
    public static ArrayList<SlideHomeFragment> listDictionary() {
        ArrayList<SlideHomeFragment> slideHomeFragmentLists = new ArrayList<>();
        slideHomeFragmentLists.add(new SlideHomeFragment("Bear", "Gấu", R.drawable.ic_bear));
        slideHomeFragmentLists.add(new SlideHomeFragment("Bee", "Ong", R.drawable.ic_bee));
        slideHomeFragmentLists.add(new SlideHomeFragment("Elk", "Nai", R.drawable.ic_elk));
        slideHomeFragmentLists.add(new SlideHomeFragment("Flog", "Ếch", R.drawable.ic_frog));
        slideHomeFragmentLists.add(new SlideHomeFragment("Kangaroo", "Chuột túi", R.drawable.ic_kangaroo));
        return slideHomeFragmentLists;
    }

}
