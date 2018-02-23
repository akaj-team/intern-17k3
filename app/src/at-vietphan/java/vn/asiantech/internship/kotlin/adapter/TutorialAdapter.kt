package vn.asiantech.internship.kotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import vn.asiantech.internship.kotlin.models.ItemWelcome
import vn.asiantech.internship.kotlin.fragments.TutorialFragment

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 05/02/2018.
 */
class TutorialAdapter(fm: FragmentManager?, private var mItemWelcomeList: MutableList<ItemWelcome>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val tutorialFragment = TutorialFragment()
        tutorialFragment.setTutorialFragment(mItemWelcomeList[position])
        return tutorialFragment
    }

    override fun getCount(): Int {
        return mItemWelcomeList.size
    }
}
