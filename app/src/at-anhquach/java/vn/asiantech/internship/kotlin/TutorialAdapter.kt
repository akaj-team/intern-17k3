package vn.asiantech.internship.kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 *
 * Created by anh.quach on 2/2/18.
 */
class TutorialAdapter(fm: FragmentManager, private var tutorials: MutableList<Tutorial>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return TutorialFragment.newInstance(tutorials[position])
    }

    override fun getCount(): Int {
        return tutorials.size
    }
}
