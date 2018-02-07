package vn.asiantech.internship.ui.tutorial_kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import vn.asiantech.internship.ui.tutorial_kotlin.fragment.WelcomeFragment

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/02/2018.
 */
class Tutorial2Adapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return WelcomeFragment.newInstance(Tutorial.WELCOME)
            1 -> return WelcomeFragment.newInstance(Tutorial.SIGNUP)
            2 -> return WelcomeFragment.newInstance(Tutorial.JOINUP)
        }
        return null
    }
    override fun getCount(): Int {
        return 3
    }
}