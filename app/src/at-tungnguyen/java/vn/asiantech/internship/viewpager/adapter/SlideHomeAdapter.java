package vn.asiantech.internship.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.asiantech.internship.viewpager.model.Animal;
import vn.asiantech.internship.viewpager.ui.SlideHomeFragment;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 14/12/2017.
 */
public class SlideHomeAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Animal> mAnimals;

    public SlideHomeAdapter(FragmentManager fm, ArrayList<Animal> animals) {
        super(fm);
        this.mAnimals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        SlideHomeFragment slideHomeFragment = new SlideHomeFragment();
        slideHomeFragment.getAnimal(mAnimals.get(position));
        return slideHomeFragment;
    }

    @Override
    public int getCount() {
        return mAnimals.size();
    }

}
