package vn.asiantech.internship.ui.tutorial_kotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import vn.asiantech.internship.ui.tutorial_kotlin.fragment.SignupFragment
import vn.asiantech.internship.ui.tutorial_kotlin.fragment.WelcomeFragment

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 03/02/2018.
 */
class Tutorial2Adapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return WelcomeFragment()
            1 -> return SignupFragment()
            else -> return null
        }

    }

    override fun getCount(): Int {
        return 2
    }
}