package vn.asiantech.internship.ui.tutorialkotlin

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import vn.asiantech.internship.models.Tutorial

/**
 * Created by hoangnhat on 02/02/2018.
 * TutorialAdapter
 */
class TutorialAdapter(private var mListTutorial: MutableList<Tutorial>, var fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = TutorialItemFragment.newInstance(mListTutorial[position])

    override fun getCount(): Int {
        return mListTutorial.size
    }
}
