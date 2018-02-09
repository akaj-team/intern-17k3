package vn.asiantech.internship.loginkotlintutorial.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import vn.asiantech.internship.loginkotlintutorial.models.Screen
import vn.asiantech.internship.loginkotlintutorial.ui.TutorialItemFragment

/**
 * Created by TienHuynh on 02/02/2018.
 * AsianTech Co., Ltd
 */
class TutorialSlideAdapter(var fm: FragmentManager, private var tutorialsList: MutableList<Screen>) :
        FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TutorialItemFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return tutorialsList.size
    }

}
