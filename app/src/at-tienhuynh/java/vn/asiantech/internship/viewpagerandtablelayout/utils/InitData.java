package vn.asiantech.internship.viewpagerandtablelayout.utils;

import java.util.ArrayList;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.models.SlideHome;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public final class InitData {
    public static ArrayList<SlideHome> listDictionary() {
        ArrayList<SlideHome> slideHomeFragmentLists = new ArrayList<>();
        slideHomeFragmentLists.add(new SlideHome("Bear", "Gấu", R.drawable.ic_bear));
        slideHomeFragmentLists.add(new SlideHome("Bee", "Ong", R.drawable.ic_bee));
        slideHomeFragmentLists.add(new SlideHome("Elk", "Nai", R.drawable.ic_elk));
        slideHomeFragmentLists.add(new SlideHome("Flog", "Ếch", R.drawable.ic_frog));
        slideHomeFragmentLists.add(new SlideHome("Kangaroo", "Chuột túi", R.drawable.ic_kangaroo));
        return slideHomeFragmentLists;
    }
}
