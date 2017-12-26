package vn.asiantech.internship.canvas.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 2017
 * Created by jackty on 25/12/2017.
 */

public final class ChartValues {

    private static List<Integer> mPeopleLists;

    /**
     * List People 1
     */
    public static List<Integer> people1Values() {
        mPeopleLists = new ArrayList<>();
        mPeopleLists.add(1);
        mPeopleLists.add(7);
        mPeopleLists.add(5);
        mPeopleLists.add(9);
        mPeopleLists.add(9);
        mPeopleLists.add(6);
        mPeopleLists.add(5);
        return mPeopleLists;
    }

    /**
     * List People 2
     */
    public static List<Integer> people2Values() {
        mPeopleLists = new ArrayList<>();
        mPeopleLists.add(1);
        mPeopleLists.add(5);
        mPeopleLists.add(8);
        mPeopleLists.add(2);
        mPeopleLists.add(2);
        mPeopleLists.add(4);
        mPeopleLists.add(10);
        return mPeopleLists;
    }

    /**
     * List People 3
     */
    public static List<Integer> people3Values() {
        mPeopleLists = new ArrayList<>();
        mPeopleLists.add(5);
        mPeopleLists.add(3);
        mPeopleLists.add(11);
        mPeopleLists.add(2);
        mPeopleLists.add(7);
        mPeopleLists.add(4);
        mPeopleLists.add(7);
        return mPeopleLists;
    }
}
