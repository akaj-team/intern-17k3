package vn.asiantech.internship.ui.kotlin.tutorial

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import vn.asiantech.internship.ui.kotlin.tutorial.fragment.WelcomeFragment

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/02/2018.
 */
class TutorialAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return WelcomeFragment.newInstance(TutorialEnum.WELCOME)
            1 -> return WelcomeFragment.newInstance(TutorialEnum.SIGNUP)
            2 -> return WelcomeFragment.newInstance(TutorialEnum.JOINUP)
        }
        return null
    }
    override fun getCount(): Int {
        return 3
    }
}
